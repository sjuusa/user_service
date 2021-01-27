package com.sju.myapp.Controller;

import com.sju.myapp.Exception.ResourceNotFoundException;
import com.sju.myapp.Model.Person;
import com.sju.myapp.Model.Address;
import com.sju.myapp.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("address")
public class AddressController
{
	@Autowired
	private AddressService addressService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> getAddressById(@Valid @PathVariable(value = "id") String id)
			throws ResourceNotFoundException
	{
		return new ResponseEntity<>(addressService.getById(Integer.parseInt(id)), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Page<Address>> getAddressAll(Pageable pageable)
	{
		return new ResponseEntity<>(addressService.getAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/searchPersons")
	public ResponseEntity<List<Person>> getPersonsByCountry(@RequestParam String country)
			throws ResourceNotFoundException
	{
		return new ResponseEntity<>(addressService.getPersonsByCountry(country), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address)
			throws ResourceNotFoundException
	{
		return new ResponseEntity<>(addressService.add(address), HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Address> UpdateLogin(
			@Valid @RequestBody Address address,
			@Valid @PathVariable(value = "id") String id
	)
			throws ResourceNotFoundException
	{
		return new ResponseEntity<>(addressService.update(address, Integer.parseInt(id)), HttpStatus.OK);
	}

}
