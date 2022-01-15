package com.BridgeLabz.HotelReservation;
import java.time.LocalDate;
import java.util.ArrayList;

public interface HotelRegistrationInterface {

    public void addHotel(String hotelName,int rating, double weekdayRegularCustomerCost, double weekendRegularCustomerCost);
    public int getHotelListSize();
    public void printHotelList();
    public ArrayList<Hotel> getHotelList();
    public ArrayList<Hotel> getCheapestHotel(LocalDate startDate, LocalDate endDate);
    public Hotel getCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate);
    public Hotel getBestRatedHotel(LocalDate startDate, LocalDate endDate);
}