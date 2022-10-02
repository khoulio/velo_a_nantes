package com.formation.velo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.formation.velo.api.OpenDataVeloNantes;
import com.formation.velo.api.data.OpenDataVeloNante;
import com.formation.velo.model.User;
import com.formation.velo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Controller
@RequestMapping("/api")
public class UserController {
	
	private UserRepository userRepository;
	
	@GetMapping("users")
	public ResponseEntity<List<User>> getAll(){
		List<User> users = userRepository.findAll();
		
		return ResponseEntity.ok(users);
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

	@GetMapping("users/{id}")
	public ResponseEntity<Optional<User>> getPersoneById(@PathVariable Integer id){
		Optional<User> user = userRepository.findById(id);
		
		return ResponseEntity.ok(user);
	}

	@PostMapping("users/add")
	public ResponseEntity<User> add(@RequestParam String name,@RequestParam String surname){
		User user = User.builder().name(name).surname(surname).build();
		return ResponseEntity.ok(userRepository.save(user));
	}

	@DeleteMapping("users/delete")
	public ResponseEntity<String> delete(@RequestParam Integer id){
		userRepository.deleteById(id);
		return ResponseEntity.ok("deleted");
	}

	@PostMapping("users/update")
	public ResponseEntity<String> delete(@RequestBody User user){
		userRepository.save(user);
		return ResponseEntity.ok("updated");
	}


	

}
