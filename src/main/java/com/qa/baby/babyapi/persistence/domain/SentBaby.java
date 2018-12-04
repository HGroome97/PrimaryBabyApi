package com.qa.baby.babyapi.persistence.domain;

import java.util.Date;

public class SentBaby {

	private Long id;

    private String name;
    
    private String birthday;
    
    public SentBaby() {
    }

    public SentBaby(Long babyId, String name, String birthday) {
        this.id = babyId;
    	this.name = name;
    	this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
	
	@Override
	public String toString() {
		return "Baby [babyId=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}


}
