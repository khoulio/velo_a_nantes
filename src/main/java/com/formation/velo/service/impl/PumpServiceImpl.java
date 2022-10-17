package com.formation.velo.service.impl;

import com.formation.velo.api.OpenData;
import com.formation.velo.api.client.OpenDataNantesClient;
import com.formation.velo.api.client.OpenDataPumpClient;
import com.formation.velo.model.Pump;
import com.formation.velo.repository.PumpRepository;
import com.formation.velo.service.PumpService;
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
public class PumpServiceImpl implements PumpService {

    private final PumpRepository pumpRepository;

    public PumpServiceImpl(PumpRepository repository) {
        this.pumpRepository = repository;
    }

    @Override
    public List<Pump> findAll() {
        return pumpRepository.findAll();
    }

    @Override
    public Optional<Pump> findById(Integer id) {
        return pumpRepository.findById(id);
    }

    @Override
    public Pump save(Pump pump) {
        return pumpRepository.save(pump);
    }

    @Override
    public void deleteById(Integer id) {
        pumpRepository.deleteById(id);
    }

    @Override
    public void delete(Pump pump) {
        pumpRepository.delete(pump);
    }

    @Override
    public void saveRecords() {
        // 1 appel opendata
        String baseUrl = "https://data.economie.gouv.fr/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenDataPumpClient client = retrofit.create(OpenDataPumpClient.class);
        Call<OpenData> openDataVeloNantesCall = client.getRecords("nantes");
        try {
            OpenData openData = openDataVeloNantesCall.execute().body();
            assert openData != null;
            log.info("Pumps to create or update "+ openData.getRecords().length);


            Arrays.stream(openData.getRecords()).forEach(record -> {
                Optional<Pump> pumpToUpdate = findByRecordId(record.getRecordId());
                if(pumpToUpdate.isPresent()){
                    // on update la pump


                    // on save
                    save(pumpToUpdate.get());
                }else {
                    // on crée la pump
                    Pump newPump = Pump.builder()
                            .recordId(record.getRecordId())
                            .build();
                    // on save

                    save(newPump);
                }
            });

            // 2 Save records dans pumps
            //


        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public Optional<Pump> findByRecordId(String recordId) {
        return pumpRepository.findByRecordId(recordId);
    }


}
