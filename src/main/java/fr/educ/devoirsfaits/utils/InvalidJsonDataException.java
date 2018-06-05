package fr.educ.devoirsfaits.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class InvalidJsonDataException extends JsonProcessingException {

    public InvalidJsonDataException(String msg, Throwable rootCause) {
        super(msg, rootCause);
    }
}
