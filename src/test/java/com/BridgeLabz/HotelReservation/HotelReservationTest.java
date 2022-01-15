package com.BridgeLabz.HotelReservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelReservationTest {
    @Test
    public void givenHotelDetails_WhenValuesEnteredAreCorrect_ShoulReturnTrue()
    {
        HotelReservation hotelReservationService = new HotelReservation();
        boolean isValidHotel1 = hotelReservationService.addHotel("Lakewood", 3, 110, 90);
        Assertions.assertTrue(isValidHotel1);
        boolean isValidHotel2 = hotelReservationService.addHotel("Bridgewood", 4, 16, 60);
        Assertions.assertTrue(isValidHotel2);
        boolean isValidHotel3 = hotelReservationService.addHotel("Ridgewood", 5, 220, 150);
        Assertions.assertTrue(isValidHotel3);
    }
}