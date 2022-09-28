package com.formation.velo.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -7670709104974486420L;
	
	private Integer id;
	private String name;
	private String surname;
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
