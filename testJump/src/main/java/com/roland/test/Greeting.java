package com.roland.test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {
	@JsonProperty("id")
	private int id;
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("list")
	private List<String> list;

	public Greeting(int id, String content, String date, List<String> list) {
		this.id = id;
		this.content = content;
		this.list = list;
	}

	public Greeting() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
        this.id = id;
    }
	
	public String getContent() {
		return content;
	}
	
	 public void setContent(String content) {
	        this.content = content;
	    }
	 
	 public String getDate() {
		 return this.date;
	 }
	 
	 public void setDate() {
		 this.date = date;
	 }

	 public List<String> getList() {
		 return this.list;
	 }
	 
	 public void setList() {
		 this.list = list;
	 }
}
