package com.scotiabank.reactiveapi.student.util.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("No student was found for the provided ID.");
    }

}
