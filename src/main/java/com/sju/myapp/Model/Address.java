package com.sju.myapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;

	@NotNull
	@Column(name = "address_1")
	private String address1;

	@NotNull
	@Column(name = "address_2")
	private String address2;

	@NotNull
	private String city;

	@NotNull
	private String state;

	@NotNull
	private String zip;

	@NotNull
	private String country;

	@ManyToMany(mappedBy = "addressList", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<Person> personList = new ArrayList<>();

	public void addPerson(Person person)
	{
		this.personList.add(person);
	}
}
