package com.codingdojo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// only empty construct if using model
	
	
	@NotNull(message="you must provide a name ")
	@Size(min = 2, message="The name must have at least 2 characters")
	private String name;
	
	@NotNull(message="you must provide your driver's licens No ")
	@Size(min = 8,max =8, message="Drivers licens No  must be 8 characters long")
	private String dlno;
	
	
	
	@NotNull
	@NotEmpty(message="you must provide your email")
	private String email;
	
	
	@NotNull
	@NotEmpty(message="you must provide a password ")
	@Size(min=8, message="Confirm Password must be between 8 and 128 characters")
	private String password;
	
	
	@Transient
	@NotEmpty(message="Confirm Password is required!")
    @Size(min=8, message="Confirm Password must be between 8 and 128 characters")
	private String confirmPassword;
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Vehicle> vehicles;
	
	 @PrePersist
	 protected void onCreate(){
		 this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate(){
	     this.updatedAt = new Date();
	 }
	public Long getId() {
		return id;
	}
	
	public User() {}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	//	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getDlno() {
		return dlno;
	}
	public void setDlno(String dlno) {
		this.dlno = dlno;
	}



}
