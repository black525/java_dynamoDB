package com.springboot.elasticbeanstalk.com.springbootEBS;

import com.springboot.elasticbeanstalk.com.springbootEBS.entity.Person;
import com.springboot.elasticbeanstalk.com.springbootEBS.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringbootEbsApplication {

	private PersonRepository personRepository;

	@PostMapping("/savePerson")
	public Person addPerson(@RequestBody Person person){
		return  personRepository.addPerson(person);
	}

	@GetMapping("/findPerson/{personId}")
	public Person findPerson(@PathVariable String personId){
		return personRepository.findPersonByPersonId(personId);
	}

	@DeleteMapping("/deletePerson")
	public String deletePerson(@RequestBody Person person){
		return personRepository.deletePerson(person);
	}

	@DeleteMapping("/deletePersonByExpression")
	public String deletePersonByExpression(@RequestBody Person person){
		return personRepository.deletePersonByExpression(person);
	}

	@PutMapping("/updatePerson")
	public String updatePerson(@RequestBody Person person){
		return personRepository.editPerson(person);
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbootEbsApplication.class, args);
	}

}
