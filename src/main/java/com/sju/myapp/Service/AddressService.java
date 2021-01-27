package com.sju.myapp.Service;

import java.util.ArrayList;
import java.util.Optional;
import lombok.NoArgsConstructor;
import com.sju.myapp.Exception.ResourceNotFoundException;
import com.sju.myapp.Model.Person;
import com.sju.myapp.Model.Address;
import com.sju.myapp.Repository.AddressRepository;
import com.sju.myapp.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class AddressService
{
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PersonRepository personRepository;

	public Page<Address> getAll(Pageable pageable)
	{
		return addressRepository.findAll(pageable);
	}

	public Address add(Address address)
	{
		return addressRepository.save(address);
	}

	public Address update(Address newAddress, int id)
	{
		Address address = checkIfIdIsPresentandReturnAddress(id);
		address.setAddress1(newAddress.getAddress1());
		address.setAddress2(newAddress.getAddress2());
		address.setCity(newAddress.getCity());
		address.setState(newAddress.getState());
		address.setZip(newAddress.getZip());
		address.setCountry(newAddress.getCountry());
		return addressRepository.save( address );
	}

	public Address getById(int id) throws ResourceNotFoundException
	{
		return checkIfIdIsPresentandReturnAddress(id);
	}

	public List<Person> getPersonsByCountry(String country)
	{
		List<Address> addressList = checkIfCountryIsPresentandReturnAddress(country);
		List<Person> personList = new ArrayList<>();
		for(Address address: addressList) {
			personList.addAll(address.getPersonList());
		}
		return personList;
	}

	private Address checkIfIdIsPresentandReturnAddress(int id)
	{
		Optional<Address> address = addressRepository.findById(id);
		if (!address.isPresent())
			throw new ResourceNotFoundException("Address id = " + id + " not found.");
		else
			return address.get();
	}

	private List<Address> checkIfCountryIsPresentandReturnAddress(String country)
	{
		List<Address> addressList = addressRepository.findByCountry(country);
		if (addressList.size() == 0)
			throw new ResourceNotFoundException("Address country = " + country + " not found.");
		else
			return addressList;
	}
}
