package com.formation.velo.service.impl;

import com.formation.velo.api.client.OpenDataNantesClient;
import com.formation.velo.api.OpenData;
import com.formation.velo.model.Station;
import com.formation.velo.repository.StationRepository;
import com.formation.velo.service.StationService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository repository) {
        this.stationRepository = repository;
    }

    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> findById(Integer id) {
        return stationRepository.findById(id);
    }

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public void deleteById(Integer id) {
        stationRepository.deleteById(id);
    }

    @Override
    public void delete(Station station) {
        stationRepository.delete(station);
    }

    @Override
    public void saveRecords() {
        // 1 appel opendata
        String baseUrl = "https://data.nantesmetropole.fr/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenDataNantesClient client = retrofit.create(OpenDataNantesClient.class);
        Call<OpenData> openDataVeloNantesCall = client.getRecords("nantes");
        try {
            OpenData openData = openDataVeloNantesCall.execute().body();
            assert openData != null;
            log.info("Stations to create or update "+ openData.getRecords().length);


            Arrays.stream(openData.getRecords()).forEach(record -> {
                Optional<Station> stationToUpdate = findByRecordId(record.getRecordId());
                if(stationToUpdate.isPresent()){
                    // on update la station
                    stationToUpdate.get()
                            .setBikeStands(record.getField().getBikeStands());
                    stationToUpdate.get()
                            .setAddress(record.getField().getAddress());
                    stationToUpdate.get()
                            .setAvailableBikes(record.getField().getAvailableBikes());
                    stationToUpdate.get()
                            .setAvailableBikeStands(record.getField().getAvailableBikeStands());
                    stationToUpdate.get().setStatus(record.getField().getStatus());

                    // on save
                    save(stationToUpdate.get());
                }else {
                    // on crée la station
                    Station newStation = Station.builder()
                            .recordId(record.getRecordId())
                            .name(record.getField().getName())
                            .address(record.getField().getAddress())
                            .availableBikes(record.getField().getAvailableBikes())
                            .bikeStands(record.getField().getBikeStands())
                            .availableBikeStands(record.getField().getAvailableBikeStands())
                            .latitude(record.getField().getPosition()[0])
                            .longitude(record.getField().getPosition()[1])
                            .status(record.getField().getStatus())
                            .build();
                    // on save

                    save(newStation);
                }
            });

            // 2 Save records dans stations
            //


        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public Optional<Station> findByRecordId(String recordId) {
        return stationRepository.findByRecordId(recordId);
    }


}
