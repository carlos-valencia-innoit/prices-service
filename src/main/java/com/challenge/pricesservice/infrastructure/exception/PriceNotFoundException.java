package com.challenge.pricesservice.infrastructure.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException() {
        super("Price not found for the given criteria");
    }
}
