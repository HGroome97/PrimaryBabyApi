package com.qa.baby.babyapi.persistence.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Baby {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private String birthday;
    
    public Baby() {
    }

    public Baby(Long babyId, String name, String birthday) {
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

	public void setBirthday(String string) {
		this.birthday = string;
	}

	@Override
	public String toString() {
		return "Baby [babyId=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}

}
