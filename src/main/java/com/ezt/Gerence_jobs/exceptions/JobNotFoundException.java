package com.ezt.Gerence_jobs.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){
        super("Job not found");
    }
}