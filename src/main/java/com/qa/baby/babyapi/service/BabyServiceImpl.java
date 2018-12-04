package com.qa.baby.babyapi.service;

import com.qa.baby.babyapi.persistence.domain.Baby;
import com.qa.baby.babyapi.persistence.domain.SentBaby;
import com.qa.baby.babyapi.persistence.repository.BabyRepository;
import com.qa.baby.babyapi.util.constants.Constants;
import com.qa.baby.babyapi.util.exceptions.BabyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BabyServiceImpl implements BabyService {

    @Autowired
    private BabyRepository repo;

    @Autowired
    private JmsTemplate jmsTemplate;
    
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
    public String addBaby(Baby baby) {
		if(Constants.bannedNames.stream().anyMatch(str -> str.trim().equals(baby.getName()))==true){
			return "Name is on banned list";
		}else if(baby.getName().length()>12) {
			return "Baby name must be less than 12 characters";
		}else {
			repo.save(baby);
			sendToQueue(baby);
	    	return baby.toString();
	    }
    }

    @Override
    public ResponseEntity<Object> deleteBaby(Long id) {
        if(BabyExists(id)){
            repo.deleteById(id);
            sendToDeleteQueue(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> updateBaby(Baby baby) {
        if(BabyExists(baby.getId())){
        	Baby babyInDb = repo.findById(baby.getId()).get();
            babyInDb.setName(baby.getName());
            babyInDb.setBirthday(baby.getBirthday());
            repo.save(babyInDb);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    private boolean BabyExists(Long id){
        Optional<Baby> babyOptional = repo.findById(id);
        return babyOptional.isPresent();
    }
    
    private void sendToQueue(Baby baby){
        SentBaby babyToStore =  new SentBaby(baby.getId(), baby.getName(), baby.getBirthday());
        jmsTemplate.convertAndSend("BabyQueue", babyToStore);
    }
    
    private void sendToDeleteQueue(long babyId){
        jmsTemplate.convertAndSend("BabyDeleteQueue", babyId);
    }
    
}
