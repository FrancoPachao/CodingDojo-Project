package com.outsidethebox.project.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Coloque su nombre completo")
	@Size(min = 8, message = "El nombre completo debe contener al menos 8 caracteres.")
	private String fullName;

	@NotEmpty(message = "Coloque su número de teléfono")
	private String phoneNumber;

	@NotEmpty(message = "Coloque su Email")
	@Email(message = "email invalido")
	private String email;

	@NotEmpty(message = "Coloque su contraseña")
	@Size(min = 6, message = "La contraseña necesita al menos 6 caracteres.")
	private String password;

	private String profilePicture;

	@NotNull
	private boolean isSupplier;

	@Transient
	@NotEmpty(message = "Coloque su contraseña otra vez")
	@Size(min = 6, message = "La contraseña necesita al menos 6 caracteres.")
	private String confirm;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<Post> supplierPost;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Order> clientOrders;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> clienReviews;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public User() {
	}

	public User(String fullName, String email, String phoneNumber, String password, String profilePicture,
			boolean isSupplier, Date createdAt) {
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.confirm = password;
		this.profilePicture = profilePicture;
		this.isSupplier = isSupplier;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
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

	public boolean isSupplier() {
		return isSupplier;
	}

	public void setSupplier(boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	public List<Review> getClienReviews() {
		return clienReviews;
	}

	public void setClienReviews(List<Review> clienReviews) {
		this.clienReviews = clienReviews;
	}

	public List<Post> getSupplierPost() {
		return supplierPost;
	}

	public void setSupplierPost(List<Post> supplierPost) {
		this.supplierPost = supplierPost;
	}

	public List<Order> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<Order> clientOrders) {
		this.clientOrders = clientOrders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + ", isSupplier=" + isSupplier + "]";
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}