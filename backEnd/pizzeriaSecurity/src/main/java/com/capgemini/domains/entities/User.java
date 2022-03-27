package com.capgemini.domains.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends EntityBase<User> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank
	@Length(max=50)
	private String username;

	@NotBlank
	@Length(max=50)
	private String address;

	@NotBlank
	@Column(name="first_name")
	@Length(max=25)
	private String firstName;

	@NotBlank
	@Length(min=2, max=100)
	private String function;

	@Column(name="last_name")
	@Length(max=25)
	private String lastName;

	@NotBlank
	@Length(min=8, max=15)
	private String password;

	public User() {
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
		
	public User(@NotBlank @Length(max = 50) String address,
			@NotBlank @Length(max = 25) String firstName, 
			@NotBlank @Length(min=2, max=100) String function, 
			@Length(max = 25) String lastName,
			@NotBlank @Length(min = 8, max = 15) String password) {
		this();
		this.address = address;
		this.firstName = firstName;
		this.function = function;
		this.lastName = lastName;
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return username.equals(other.username);
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", function=" + function + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	

}