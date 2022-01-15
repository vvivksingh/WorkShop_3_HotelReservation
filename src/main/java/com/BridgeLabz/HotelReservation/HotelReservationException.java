package com.BridgeLabz.HotelReservation;

public class HotelReservationException extends RuntimeException {

    enum ExceptionType{
        ENTERED_NULL, ENTERED_NOTHING;
    }

    ExceptionType type;

    public HotelReservationException(ExceptionType type, String message) {

        super(message);
        this.type = type;

    }
}

