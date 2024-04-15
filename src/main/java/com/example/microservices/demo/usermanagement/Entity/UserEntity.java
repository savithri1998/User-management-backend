package com.example.microservices.demo.usermanagement.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
@Table
@Entity
public class UserEntity {

	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)",unique=true)
	@Id
	private String userId;
	@Size(min=3,message="Name should have atleast 3 characters")
	//it will allow the spaces
	@NotEmpty(message="Email is required ")
	@Email(message="please enter valid Email")
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String address;
	private Date dob;
	private Boolean status;
	private String mobileNumber;
	
	public UserEntity(String userId,
			@Size(min = 3, message = "Name should have atleast 3 characters") @NotEmpty(message = "Email is required ") @Email(message = "please enter valid Email") String email,
			String firstName, String lastName, String gender, String address, Date dob, Boolean status,
			String mobileNumber) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.dob = dob;
		this.status = status;
		this.mobileNumber = mobileNumber;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", address=" + address + ", dob=" + dob + ", status=" + status
				+ ", mobileNumber=" + mobileNumber + "]";
	}
	public static UserEntity ok(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
