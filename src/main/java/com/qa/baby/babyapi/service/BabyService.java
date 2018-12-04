package com.qa.baby.babyapi.service;

import org.springframework.http.ResponseEntity;

import com.qa.baby.babyapi.persistence.domain.Baby;

import java.util.List;

public interface BabyService {

    List<Baby> getBabies();

    Baby getBaby(Long id);

    Baby addBaby(Baby baby);

    ResponseEntity<Object> deleteBaby(Long id);

    ResponseEntity<Object> updateBaby(Baby baby, Long id);
}