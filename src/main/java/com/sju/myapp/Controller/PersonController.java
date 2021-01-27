package com.sju.myapp.Controller;

import com.sju.myapp.Model.Person;
import com.sju.myapp.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping("person")
public class PersonController
{
	@Autowired
	private PersonService personService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> getAddressById(@Valid @PathVariable(value = "id") String id)
	{
		return new ResponseEntity<>(personService.getById(Integer.parseInt(id)), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Page<Person>> getAddressAll(Pageable pageable)
	{
		return new ResponseEntity<>(personService.getAll(pageable), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Person> createAddress(@Valid @RequestBody Person person)
	{
		return new ResponseEntity<>(personService.add(person), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Person> deleteAddress(@Valid @PathVariable(value = "id") String id)
	{
		return new ResponseEntity<>(personService.deleteById(Integer.parseInt(id)), HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Person> UpdateLogin(
			@Valid @RequestBody Person person,
			@Valid @PathVariable(value = "id") String id
	)
	{
		return new ResponseEntity<>(personService.update(person, Integer.parseInt(id)), HttpStatus.OK);
	}
}
