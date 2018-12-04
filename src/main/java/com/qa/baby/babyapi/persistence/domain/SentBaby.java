package com.qa.baby.babyapi.persistence.domain;

import java.util.Date;

public class SentBaby {

	private Long babyId;

    private String name;
    
    private String birthday;
    
    public SentBaby() {
    }

    public SentBaby(Long babyId, String name, String birthday) {
        this.babyId = babyId;
    	this.name = name;
    	this.birthday = birthday;
    }

    public Long getId() {
        return babyId;
    }

    public void setId(Long id) {
        this.babyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


}
