package com.sju.myapp.Service;

import com.sju.myapp.Model.Address;
import com.sju.myapp.Model.Login;
import com.sju.myapp.Repository.AddressRepository;
import com.sju.myapp.Repository.LoginRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.sju.myapp.Exception.BadUserInputException;
import com.sju.myapp.Exception.ResourceNotFoundException;
import com.sju.myapp.Model.Person;
import com.sju.myapp.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService
{
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private LoginRepository loginRepository;

	public Page<Person> getAll(Pageable pageable)
	{
		return personRepository.findAll(pageable);
	}

	public Person add(Person person)
	{
		validatePerson(person);
		processPerson(person);
		return personRepository.save(person);
	}

	public Person update(Person newPerson, int id) throws ResourceNotFoundException
	{
		Person person = checkIfIdIsPresentandReturnPerson(id);
		person.setFirstName(newPerson.getFirstName());
		person.setLastName(newPerson.getLastName());
		return personRepository.save(person);
	}

	public Person getById(int id) throws ResourceNotFoundException
	{
		return checkIfIdIsPresentandReturnPerson(id);
	}

	public Person deleteById(int id) throws ResourceNotFoundException
	{
		Person person = checkIfIdIsPresentandReturnPerson(id);
		personRepository.deleteById(id);
		return person;
	}

	private void validatePerson(Person person)
	{
		validLogin(person.getLogin());
		validAddressList(person.getAddressList());
	}

	private void validLogin(Login login)
	{
		if (login != null && login.getId() != 0) {
			throw new BadUserInputException("login id is a read only property.");
		}
		checkIfEmailIsPresent(login.getEmail());
	}

	private void validAddressList(List<Address> addressList)
	{
		if (addressList == null) {
			return;
		}
		for (Address address: addressList) {
			if (address.getId() != 0) {
				checkIfIdIsPresentandReturnAddress(address.getId());
			}
		}
	}

	private void processPerson(Person person)
	{
		Login login = person.getLogin();
		List<Address> inputAddressList = person.getAddressList();

		if (login != null) {
			login.setPerson(person);
			person.setLogin(login);
		}

		List<Address> addressList = new ArrayList<>();
		if (inputAddressList != null) {
			for (Address address : inputAddressList) {
				if (address.getId() != 0) {
					address = checkIfIdIsPresentandReturnAddress(address.getId());
				}
				address.addPerson(person);
				addressList.add(address);
			}
		}
		person.setAddressList(addressList);
	}

	private Person checkIfIdIsPresentandReturnPerson(int id)
	{
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent())
			throw new ResourceNotFoundException("Person id=" + id + " not found.");
		else
			return person.get();
	}

	private void checkIfEmailIsPresent(String email)
	{
		Optional<Login> login = loginRepository.findByEmail(email);
		if (login.isPresent())
			throw new BadUserInputException("Login email=" + email + " already exists.");
	}

	private Address checkIfIdIsPresentandReturnAddress(int id)
	{
		Optional<Address> address = addressRepository.findById(id);
		if (!address.isPresent())
			throw new ResourceNotFoundException("Address id=" + id + " not found.");
		else
			return address.get();
	}
}
