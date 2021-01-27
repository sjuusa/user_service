package com.sju.myapp.Controller;

import com.sju.myapp.Exception.ResourceNotFoundException;
import com.sju.myapp.Model.Login;
import com.sju.myapp.Model.Person;
import com.sju.myapp.Service.LoginService;
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
@RequestMapping("login")
public class LoginController
{
	@Autowired
	private LoginService loginMainService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Login> getLoginById(@Valid @PathVariable(value = "id") String id)
	{
		return new ResponseEntity<>(loginMainService.getById(Integer.parseInt(id)), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Page<Login>> getLoginAll(Pageable pageable)
	{
		return new ResponseEntity<>(loginMainService.getAll(pageable), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Login> createLogin(@Valid @RequestBody Login login)
	{
		return new ResponseEntity<>(loginMainService.add(login), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deletePerson")
	public ResponseEntity<Person> deletePersonByEmail(@RequestParam String email)
			throws ResourceNotFoundException
	{
		return new ResponseEntity<>(loginMainService.deletePersonByEmail(email), HttpStatus.OK);
	}


	@PatchMapping(value = "/{id}")
	public ResponseEntity<Login> UpdateLogin(
			@Valid @RequestBody Login login,
			@Valid @PathVariable(value = "id") String id
	)
	{
		return new ResponseEntity<>(loginMainService.update(login,Integer.parseInt(id)), HttpStatus.OK);
	}
}
