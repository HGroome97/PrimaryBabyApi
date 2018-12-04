package com.qa.baby.babyapi.rest;

import com.qa.baby.babyapi.persistence.domain.Baby;
import com.qa.baby.babyapi.persistence.domain.SentBaby;
import com.qa.baby.babyapi.service.BabyService;
import com.qa.baby.babyapi.util.constants.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RequestMapping("${path.base}")
@RestController
public class BabyRest {

    @Autowired
    private BabyService service;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Value("${url.generator}")
    private String generatorURL;
    
    @Value("${path.genBabyName}")
    private String babyNameGeneratorPath;
    
    
    
    @GetMapping("${path.getBabies}")
    public List<Baby> getBabies() {
        return service.getBabies();
    }

    @GetMapping("${path.getBabyById}")
    public Baby getBaby(@PathVariable Long id) {
        return service.getBaby(id);
    }

    @DeleteMapping("${path.deleteBaby}")
    public ResponseEntity<Object> deleteBaby(@PathVariable Long id) {
        return service.deleteBaby(id);
    }

    @PutMapping("${path.updateBaby}")
    public ResponseEntity<Object> updateBaby(@RequestBody Baby baby) {
        return service.updateBaby(baby);
    }

    @GetMapping("${path.createBaby}")
    public String createBaby(@PathVariable int length, @PathVariable String start) {
        Baby baby = new Baby();
        setBabyName(baby, length, start);
        setBabyBirthday(baby);
    	return service.addBaby(baby);
    }

    private Baby setBabyName(Baby baby, int length, String start){
        String generatedBabyName = restTemplate.getForObject(generatorURL + babyNameGeneratorPath + length + "/" + start , String.class);
        baby.setName(generatedBabyName);
        return baby;
    }
    
    private Baby setBabyBirthday(Baby baby) {
    	baby.setBirthday(new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()));
    	return baby;
    }


}
