package com.BridgeLabz.HotelReservation;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelReservationTest {

    @Test
    public void givenHotelDetails_WhenValuesEnteredAreCorrect_ShoulReturnTrue()
    {
        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110);
        hotelReservation.addHotel("Bridgewood", 4, 160);
        hotelReservation.addHotel("Ridgewood", 5, 220);
        int hotelListSize = hotelReservation.getHotelListSize();
        hotelReservation.printHotelList();
        Assertions.assertEquals(3, hotelListSize);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelName(){
        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 160);
        String hotelName = hotelReservation.getHotelList().get(0).getHotelName();
        Assertions.assertEquals("Bridgewood", hotelName);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelRating(){
        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 160);
        int hotelRating = hotelReservation.getHotelList().get(0).getRating();
        Assertions.assertEquals(4, hotelRating);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelRegularCustomerCost(){
        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 160);
        int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getRegularCustomerCost();
        Assertions.assertEquals(160, hotelRegularCustomerCost);
    }

    @Test
    public void givenHotelDetails_shouldReturnCheapestHotel(){

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110);
        hotelReservation.addHotel("Bridgewood", 4, 160);
        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 10);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        Hotel hotel = hotelReservation.getCheapestHotel(startDate, endDate);
        Assertions.assertEquals("Lakewood", hotel.getHotelName());
    }
}