package com.shahprojects.stocktrading.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String msg){
        super(msg);
    }

}
