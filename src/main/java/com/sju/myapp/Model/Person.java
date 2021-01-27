package com.sju.myapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;

	@NotNull
	@Size(max = 65)
	private String firstName;

	@NotNull
	@Size(max = 65)
	private String lastName;


	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "login_id", nullable = true)
	private Login login;


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "personAddress", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Address> addressList;
}
