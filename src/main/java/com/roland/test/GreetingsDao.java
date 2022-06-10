package com.roland.test;

import java.util.HashMap;
import java.util.Map;

public class GreetingsDao {

	private Map<Integer, Greeting> greetingList;
	
	public GreetingsDao(Map<Integer, Greeting> greetingList) {
		this.greetingList = greetingList;
	}
	
	public Map<Integer, Greeting> getGreetingList() {
        return this.greetingList;
    }
	
	public void setGreetingList(Map<Integer, Greeting> greetingList) {
        this.greetingList = greetingList;
    }
}
