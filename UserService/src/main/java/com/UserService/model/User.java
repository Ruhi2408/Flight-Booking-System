package com.UserService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "name", nullable = false)
    private String passengerName;
    
    @Column(name="age",nullable= false)
    private int age;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name="contact_no", nullable= false,unique=true)
    private String contact_no;

    @Column(name = "password", nullable = false)
    private String password;
    
    

    public User() {
	}
	public User(Long user_id, String passengerName,int age, String email, String password,String contact_no) {
		super();
		this.user_id = user_id;
		this.passengerName = passengerName;
		this.age=age;
		this.email = email;
		this.password = password;
		this.contact_no = contact_no;
	}// Getters and Setters
	
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public Long getId() { return user_id; }
    
   
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setId(Long user_id) { this.user_id = user_id; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}