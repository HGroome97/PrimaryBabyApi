package com.qa.baby.babyapi.service;

import com.qa.baby.babyapi.persistence.domain.Baby;
import com.qa.baby.babyapi.persistence.repository.BabyRepository;
import com.qa.baby.babyapi.util.exceptions.BabyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BabyServiceImpl implements BabyService {

    @Autowired
    private BabyRepository repo;

    @Override
    public List<Baby> getBabies() {
        return repo.findAll();
    }

    @Override
    public Baby getBaby(Long id) {
        Optional<Baby> Baby = repo.findById(id);
        return Baby.orElseThrow(() -> new BabyNotFoundException(id.toString()));
    }

    @Override
    public Baby addBaby(Baby Baby) {
        return repo.save(Baby);
    }

    @Override
    public ResponseEntity<Object> deleteBaby(Long id) {
        if(BabyExists(id)){
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> updateBaby(Baby Baby, Long id) {
        if(BabyExists(id)){
            Baby.setId(id);
            repo.save(Baby);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    private boolean BabyExists(Long id){
        Optional<Baby> BabyOptional = repo.findById(id);
        return BabyOptional.isPresent();
    }

}
