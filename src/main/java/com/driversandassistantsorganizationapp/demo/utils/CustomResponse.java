package com.driversandassistantsorganizationapp.demo.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {
    @NotNull
    @Contract("_ -> new")
    // status code 200
    public static ResponseEntity successResponse(Object body) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @NotNull
    @Contract("_ -> new")
    // status code 400
    public static ResponseEntity badRequestResponse(Object body) {
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @NotNull
    @Contract("_ -> new")
    // status code 404
    public static ResponseEntity notFoundResponse(Object body) {
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @NotNull
    @Contract("_ -> new")
    // status code 403
    public static ResponseEntity deniedResponse(Object body) {
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}