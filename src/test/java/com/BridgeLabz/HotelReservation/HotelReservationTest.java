package com.BridgeLabz.HotelReservation;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelReservationTest {

    @Test
    public void givenHotelDetails_WhenValuesEnteredAreCorrect_ShoulReturnTrue() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
        int hotelListSize = hotelReservation.getHotelListSize();
        hotelReservation.printHotelList();
        Assertions.assertEquals(3, hotelListSize);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelName() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        String hotelName = hotelReservation.getHotelList().get(0).getHotelName();
        Assertions.assertEquals("Bridgewood", hotelName);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelRating() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
        int hotelRating = hotelReservation.getHotelList().get(0).getRating();
        Assertions.assertEquals(5, hotelRating);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekdayRegularCustomerCost() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekdayRegularCustomerCost();
        Assertions.assertEquals(150, hotelRegularCustomerCost);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekendRegularCustomerCost() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekendRegularCustomerCost();
        Assertions.assertEquals(50, hotelRegularCustomerCost);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekdayRewardCustomerCost() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        int hotelRewardCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekdayRewardCustomerCost();
        hotelReservation.printHotelList();
        Assertions.assertEquals(110, hotelRewardCustomerCost);
    }

    @Test
    public void givenHotelList_WhenAdded_shouldReturnProperHotelWeekendRewardCustomerCost() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        int hotelRegularCustomerCost = (int) hotelReservation.getHotelList().get(0).getWeekendRewardCustomerCost();
        hotelReservation.printHotelList();
        Assertions.assertEquals(50, hotelRegularCustomerCost);
    }

    @Test
    public void givenHotelDetails_WhenRegularCustomer_shouldReturnCheapestHotel() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);

        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
        String startDateToValidate = startDate.toString();
        String endDateToValidate = endDate.toString();
        boolean isValidStartDate = hotelReservation.validateDate(startDateToValidate);
        boolean isValidEndDate = hotelReservation.validateDate(endDateToValidate);

        if(isValidStartDate && isValidEndDate) {
            ArrayList<Hotel> hotel = hotelReservation.getCheapestHotel("regular", startDate, endDate);
            String hotelName = hotel.get(0).getHotelName() + " " + hotel.get(1).getHotelName();
            Assertions.assertEquals("Lakewood Bridgewood", hotelName);
        }
        else {
            System.out.println("Not Valid Start Or End date");
        }
    }

    @Test
    public void givenHotelDetails_WhenRegularCustomerAndHotelPricesAreSame_shouldReturnHighestRatedHotel() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);

        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
        String startDateToValidate = startDate.toString();
        String endDateToValidate = endDate.toString();
        boolean isValidStartDate = hotelReservation.validateDate(startDateToValidate);
        boolean isValidEndDate = hotelReservation.validateDate(endDateToValidate);

        if(isValidStartDate && isValidEndDate) {
            Hotel hotel = hotelReservation.getCheapestBestRatedHotel("regular", startDate, endDate);
            Assertions.assertEquals("Bridgewood", hotel.getHotelName());
        }
        else {
            System.out.println("Not Valid Start Or End date");
        }
    }

    @Test
    public void givenHotelDetails_WhenRegularCustomer_ShouldReturnHighestRatedHotel() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);

        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
        String startDateToValidate = startDate.toString();
        String endDateToValidate = endDate.toString();
        boolean isValidStartDate = hotelReservation.validateDate(startDateToValidate);
        boolean isValidEndDate = hotelReservation.validateDate(endDateToValidate);

        if(isValidStartDate && isValidEndDate) {
            Hotel hotel = hotelReservation.getBestRatedHotel("regular", startDate, endDate);
            Assertions.assertEquals("Ridgewood", hotel.getHotelName());
        }
        else {
            System.out.println("Not Valid Start Or End date");
        }
    }

    @Test
    public void givenHotelDetails_WhenRewardCustomer_ShouldReturnCheapestHotel() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
        ArrayList<Hotel> hotel = hotelReservation.getCheapestHotel("reward", startDate, endDate);
        String hotelName = hotel.get(0).getHotelName() + " " + hotel.get(1).getHotelName();
        Assertions.assertEquals("Lakewood Bridgewood", hotelName);
    }

    @Test
    public void givenHotelDetails_WhenRewardCustomerAndHotelPricesAreSame_ShouldReturnHighestRatedHotel() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
        Hotel hotel = hotelReservation.getCheapestBestRatedHotel("reward", startDate, endDate);
        Assertions.assertEquals("Bridgewood", hotel.getHotelName());
    }

    @Test
    public void givenHotelDetails_WhenRewardCustomer_ShouldReturnHighestRatedHotel() {

        HotelRegistrationInterface hotelReservation = new HotelReservation();
        hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
        hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
        hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
        LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
        LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
        Hotel hotel = hotelReservation.getBestRatedHotel("reward", startDate, endDate);
        Assertions.assertEquals("Ridgewood", hotel.getHotelName());
    }

    @Test
    public void givenHotelDetails_WhenNull_ShouldThrowHotelReservationException() {


        try {
            HotelReservation hotelReservation = new HotelReservation();
            hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
            hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
            hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
            LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
            LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
            hotelReservation.getCheapestBestRatedHotel(null, startDate, endDate);
        }
        catch(HotelReservationException e){
            Assertions.assertEquals(HotelReservationException.ExceptionType.ENTERED_NULL, e.type);
            e.printStackTrace();
        }

    }

    @Test
    public void givenHotelDetails_WhenEmpty_ShouldThrowHotelReservationException() {


        try {
            HotelReservation hotelReservation = new HotelReservation();
            hotelReservation.addHotel("Lakewood", 3, 110, 90, 80, 80);
            hotelReservation.addHotel("Bridgewood", 4, 150, 50, 110, 50);
            hotelReservation.addHotel("Ridgewood", 5, 220, 150, 100, 40);
            LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 11);
            LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 12);
            hotelReservation.getCheapestBestRatedHotel("", startDate, endDate);
        }
        catch(HotelReservationException e){
            Assertions.assertEquals(HotelReservationException.ExceptionType.ENTERED_NOTHING, e.type);
            e.printStackTrace();
        }

    }

    @Test
    public void givenDate_WhenProper_ShouldReturnTrue() {

        HotelReservation hotelReservation = new HotelReservation();
        boolean isVAlid = hotelReservation.validateDate("2009-12-13");
        Assertions.assertTrue(isVAlid);
    }

    @Test
    public void givenDate_WhenNotProperFormat_ShouldReturnFalse() {

        HotelReservation hotelReservation = new HotelReservation();
        boolean isNotVAlid = hotelReservation.validateDate("19-10-1999");
        Assertions.assertFalse(isNotVAlid);
    }

    @Test
    public void givenDate_WhenSeperatedBySlashes_ShouldReturnFalse() {

        HotelReservation hotelReservation = new HotelReservation();
        boolean isNotVAlid = hotelReservation.validateDate("1999/09/2021");
        Assertions.assertFalse(isNotVAlid);
    }

    @Test
    public void givenDate_WhenContainsCharcters_ShouldReturnFalse() {

        HotelReservation hotelReservation = new HotelReservation();
        boolean isNotVAlid = hotelReservation.validateDate("200s-a2-19");
        Assertions.assertFalse(isNotVAlid);
    }

    @Test
    public void givenDate_WhenSpecialCharcters_ShouldReturnFalse() {

        HotelReservation hotelReservation = new HotelReservation();
        boolean isNotVAlid = hotelReservation.validateDate("2009/%%/13");
        Assertions.assertFalse(isNotVAlid);
    }

    @Test
    public void givenDate_WhenNull_ShouldThrowHotelReservationException() {

        HotelReservation hotelReservation = new HotelReservation();
        try {
            String date = null;
            hotelReservation.validateDate(date);
        }
        catch(HotelReservationException e){
            Assertions.assertEquals(HotelReservationException.ExceptionType.ENTERED_NULL,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenDate_WhenEmpty_ShouldThrowHotelReservationException() {

        HotelReservation hotelReservation = new HotelReservation();
        try {
            String date = "";
            hotelReservation.validateDate(date);
        }
        catch(HotelReservationException e){
            Assertions.assertEquals(HotelReservationException.ExceptionType.ENTERED_NOTHING,e.type);
            e.printStackTrace();
        }
    }
}

