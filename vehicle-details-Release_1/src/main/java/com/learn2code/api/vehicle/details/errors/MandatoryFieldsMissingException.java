package com.learn2code.api.vehicle.details.errors;

public class MandatoryFieldsMissingException extends Exception {
    public MandatoryFieldsMissingException(String allErrors) {
        super(allErrors);
    }
}
