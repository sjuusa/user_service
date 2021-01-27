package com.sju.myapp.Service;

import java.util.Optional;
import lombok.NoArgsConstructor;
import com.sju.myapp.Exception.ResourceNotFoundException;
import com.sju.myapp.Model.Login;
import com.sju.myapp.Model.Person;
import com.sju.myapp.Repository.LoginRepository;
import com.sju.myapp.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class LoginService
{
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private PersonRepository personRepository;

	public Page<Login> getAll(Pageable pageable)
	{
		return loginRepository.findAll(pageable);
	}

	public Login add(Login login)
	{
		return loginRepository.save(login);
	}

	public Login update(Login o, int id)
	{
		Login login = checkIfIdIsPresentandReturnLogin(id);
		login.setEmail(o.getEmail());
		login.setPassword(o.getPassword());
		return loginRepository.save(login);
	}

	public Login getById(int id)
	{
		return checkIfIdIsPresentandReturnLogin(id);
	}

	public Person deletePersonByEmail(String email)
	{
		Login login = checkIfEmailIsPresentandReturnLogin(email);
		Person person = login.getPerson();
		loginRepository.deleteById(login.getId());
		return person;
	}

	private Login checkIfIdIsPresentandReturnLogin(int id)
	{
		Optional<Login> login = loginRepository.findById(id);
		if (!login.isPresent())
			throw new ResourceNotFoundException("Login id=" + id + " not found.");
		else
			return login.get();
	}

	private Login checkIfEmailIsPresentandReturnLogin(String email)
	{
		Optional<Login> login = loginRepository.findByEmail(email);
		if (!login.isPresent())
			throw new ResourceNotFoundException("Login email=" + email + " not found.");
		else
			return login.get();
	}
}
