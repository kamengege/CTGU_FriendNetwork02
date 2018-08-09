package com.tedu.ctgu.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class User implements Serializable{
	private String name;
	private String age;
	
	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	
	

}
