package com.ezt.Gerence_jobs.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuario ja existe");
    }
}
