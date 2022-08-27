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
@Table(name="stations")
public class Station {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// only empty construct if using model
	
	
	@NotEmpty(message="you must provide business name ")
	@Size(min = 2, message="The name must have at least 2 characters")
	private String name;
	
	
	@NotNull
	@NotEmpty(message="you must provide your email")
	private String email;
	
	@NotNull(message="stock cant be empty")
	private Long stock;
	
	
	@NotNull
	@NotEmpty(message="you must provide your address")
	private String address;
	
	@NotNull
	@NotEmpty(message="you must provide your fuel station registration number")
	@Size(min=10, max=10, message="registration number must be 10 characters")
	private String registration;
	
	
	@NotEmpty(message="you must provide a password ")
	@Size(min=8, message="Confirm Password must be between 8 and 128 characters")
	private String password;
	
	
	@Transient
	@NotEmpty(message="Confirm Password is required!")
    @Size(min=8, message="Confirm Password must be between 8 and 128 characters")
	private String confirmPassword;
	
	
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public List<FuelReceipt> getRecipts() {
		return recipts;
	}
	public void setRecipts(List<FuelReceipt> recipts) {
		this.recipts = recipts;
	}
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="station", fetch = FetchType.LAZY)
	private List<FuelReceipt> recipts;
	
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
	
	public Station() {}
	
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
//	public List<Recipe> getRecipes() {
//		return recipes;
//	}
//	public void setRecipes(List<Recipe> recipes) {
//		this.recipes = recipes;
//	}
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}



}
