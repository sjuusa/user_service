package com.sju.myapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(unique=true)
	private String email;

	@NotNull
	private String password;

	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
	@JsonIgnore
	private Person person;

}
