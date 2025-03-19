package com.researchconnect.ResearchConnect.Exceptions;

public class CustomRegistrationException extends RuntimeException{
    public CustomRegistrationException(String message){
            super(message);

    }


    public CustomRegistrationException(String message, Throwable cause){
        super(message, cause);

}

public CustomRegistrationException(String message, IllegalArgumentException arg,Throwable cause){
    super(message, cause);

}
}

