package com.qa.baby.babyapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.baby.babyapi.persistence.domain.Baby;

@Repository
public interface BabyRepository extends JpaRepository<Baby, Long> {
}
