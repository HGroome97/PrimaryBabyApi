package com.qa.baby.babyapi.util.exceptions;

public class BabyNotFoundException extends RuntimeException{

    public BabyNotFoundException(String exception){
        super("Id supplied does not exist. Id: " + exception);
    }

}
