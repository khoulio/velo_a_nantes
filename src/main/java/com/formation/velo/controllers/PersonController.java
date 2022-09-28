package com.formation.velo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.formation.velo.api.OpenDataVeloNantes;
import com.formation.velo.api.data.OpenDataVeloNante;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formation.velo.model.Person;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Controller
@RequestMapping("/api")
public class PersonController {
	
	
	
	@GetMapping("persons")
	public ResponseEntity<List<Person>> getAll(){
		List<Person> persons = findAllPersons();
		
		return ResponseEntity.ok(persons);
	}

	@GetMapping("velos")
	public ResponseEntity<OpenDataVeloNante> getAllVelo() throws IOException {
		String baseUrl = "https://data.nantesmetropole.fr/";

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		OpenDataVeloNantes service = retrofit.create(OpenDataVeloNantes.class);
		Call<OpenDataVeloNante> repos = service.getAllVelos();
		OpenDataVeloNante openDataVeloNante = repos.execute().body();
		if (openDataVeloNante != null) {
			return ResponseEntity.ok(openDataVeloNante);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("persons/{id}")
	public ResponseEntity<Optional<Person>> getPersoneById(@PathVariable Integer id){
		Optional<Person> persons = findAllPersons().stream().filter(t -> t.getId().equals(id)).findFirst();
		
		return ResponseEntity.ok(persons);
	}



	public List<Person> findAllPersons() {
		List<Person> persons = new ArrayList<>();
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("Dupont");
		p1.setSurname("Jean");
		persons.add(p1);
		
		Person p2 = new Person();
		p2.setId(1);
		p2.setName("Couton");
		p2.setSurname("Marie");
		persons.add(p2);
		
		Person p3 = new Person();
		p3.setId(1);
		p3.setName("Same");
		p3.setSurname("Jean-marie");
		persons.add(p3);
		return persons;
	}

}
