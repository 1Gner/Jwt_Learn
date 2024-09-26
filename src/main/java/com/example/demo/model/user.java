package com.example.demo.model;



import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="tab_user1")
public class user {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	@Column(length = 50)
	private String name;

	@Column(length = 20 )
	private String username;

	@Column(length = 100)
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tab_user_role",joinColumns = @JoinColumn(name = "user_id"))
	@Column(name="role_id",length = 20 )
	private List<String> roles  = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}

}
