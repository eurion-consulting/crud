package org.wn.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.wn.util.ValidationUtil;

@Entity
@Table(name="users")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull @Column(nullable=false)
	private String name;
	
	@NotNull @Column(nullable=false)
	private String lastName;
	
	@NotNull @Column(unique=true, nullable=false)
	@Pattern(regexp=ValidationUtil.EMAIL_REGEX)
	private String email;
	
	@Past // works only with java.util.Date/Calendar??
	private LocalDate dateOfBirth;
	
	@CreatedDate
	private LocalDateTime created;
	
	@LastModifiedDate
	private LocalDateTime updated;
	
	@Override
	public String toString(){
		return name + " " + lastName + " (" + email + "), born: " + dateOfBirth;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	
}
