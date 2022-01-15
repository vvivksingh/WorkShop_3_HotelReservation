package com.BridgeLabz.HotelReservation;

import java.util.ArrayList;

public interface HotelRegistrationInterface {

    public void addHotel(String hotelName,int rating, double regularCustomerRate);
    public int getHotelListSize();
    public void printHotelList();
    public ArrayList<Hotel> getHotelList();
}