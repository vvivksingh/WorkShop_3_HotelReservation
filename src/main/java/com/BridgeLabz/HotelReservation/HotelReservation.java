package com.BridgeLabz.HotelReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelReservation implements HotelRegistrationInterface {

	public ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	public Hotel hotel;
	public static double cheapestPrice;

	public void addHotel(String hotelName, int rating, double weekdayRegularCustomerCost,
						 double weekendRegularCustomerCost, double weekdayRewardCustomerCost, double weekendRewardCustomerCost) {

		hotel = new Hotel();
		hotel.setHotelName(hotelName);
		hotel.setRating(rating);
		hotel.setWeekdayRegularCustomerCost(weekdayRegularCustomerCost);
		hotel.setWeekendRegularCustomerCost(weekendRegularCustomerCost);
		hotel.setWeekdayRewardCustomerCost(weekdayRewardCustomerCost);
		hotel.setWeekendRewardCustomerCost(weekendRewardCustomerCost);
		hotelList.add(hotel);
		System.out.println("Successfully ADDED !!");
	}

	public int getHotelListSize() {
		return hotelList.size();
	}

	public void printHotelList() {
		System.out.println(hotelList);
	}

	public ArrayList<Hotel> getHotelList() {
		return hotelList;
	}

	public ArrayList<Integer> getDurationOfStayDetails(LocalDate startDate, LocalDate endDate) {

		ArrayList<Integer> durationDetails = new ArrayList<Integer>();
		int numberOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
		int weekends = 0;

		while (startDate.compareTo(endDate) != 0) {
			switch (DayOfWeek.of(startDate.get(ChronoField.DAY_OF_WEEK))) {
				case SATURDAY:
					++weekends;
					break;
				case SUNDAY:
					++weekends;
					break;
				default:
					break;
			}
			startDate = startDate.plusDays(1);
		}

		int weekdays = numberOfDays - weekends;
		durationDetails.add(weekdays);
		durationDetails.add(weekends);
		return durationDetails;

	}

	public ArrayList<Hotel> getCheapestHotel(String customerType, LocalDate startDate, LocalDate endDate) {

		ArrayList<Integer> durationDetails = getDurationOfStayDetails(startDate, endDate);
		int weekdaysNumber = durationDetails.get(0);
		int weekendsNumber = durationDetails.get(1);
		ArrayList<Hotel> cheapestHotel = new ArrayList<Hotel>();

		if (customerType.equalsIgnoreCase("Regular")) {

			cheapestPrice = hotelList.stream()
					.mapToDouble(hotel -> ((hotel.getWeekendRegularCustomerCost() * weekendsNumber)
							+ hotel.getWeekdayRegularCustomerCost() * weekdaysNumber))
					.min().orElse(Double.MAX_VALUE);

			cheapestHotel = hotelList.stream()
					.filter(hotel -> (hotel.getWeekendRegularCustomerCost() * weekendsNumber
							+ hotel.getWeekdayRegularCustomerCost() * weekdaysNumber) == cheapestPrice)
					.collect(Collectors.toCollection(ArrayList::new));
		} else if (customerType.equalsIgnoreCase("Reward")) {
			cheapestPrice = hotelList.stream()
					.mapToDouble(hotel -> ((hotel.getWeekendRewardCustomerCost() * weekendsNumber)
							+ hotel.getWeekdayRewardCustomerCost() * weekdaysNumber))
					.min().orElse(Double.MAX_VALUE);

			cheapestHotel = hotelList.stream()
					.filter(hotel -> (hotel.getWeekendRewardCustomerCost() * weekendsNumber
							+ hotel.getWeekdayRewardCustomerCost() * weekdaysNumber) == cheapestPrice)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		if (cheapestPrice != Double.MAX_VALUE) {
			Iterator<Hotel> iterator = cheapestHotel.iterator();
			System.out.println("Cheap Hotels : \n");
			while (iterator.hasNext()) {
				System.out.println(iterator.next().getHotelName() + ", Total Rates: " + cheapestPrice);
			}
			return cheapestHotel;
		}
		return null;


	}

	public Hotel getCheapestBestRatedHotel(String customerType, LocalDate startDate, LocalDate endDate) {

		try {

			if(customerType.length() == 0)
				throw new HotelReservationException(HotelReservationException.ExceptionType.ENTERED_NOTHING, "EMPTY Value Entered");

			ArrayList<Hotel> cheapestHotels = getCheapestHotel(customerType, startDate, endDate);
			Optional<Hotel> sortedHotelList = cheapestHotels.stream()
					.max(Comparator.comparing(Hotel::getRating));

			System.out.println("Cheapest Best Rated Hotel : \n" + sortedHotelList.get().getHotelName() + ", Total Rates: "
					+ cheapestPrice);
			return sortedHotelList.get();
		}
		catch(NullPointerException e) {
			throw new HotelReservationException(HotelReservationException.ExceptionType.ENTERED_NULL, "NULL Value Entered");
		}
	}

	public Hotel getBestRatedHotel(String customerType, LocalDate startDate, LocalDate endDate) {

		try {

			if(customerType.length() == 0)
				throw new HotelReservationException(HotelReservationException.ExceptionType.ENTERED_NOTHING, "EMPTY Value Entered");

			ArrayList<Integer> durationDetails = getDurationOfStayDetails(startDate, endDate);
			int weekdaysNumber = durationDetails.get(0);
			int weekendsNumber = durationDetails.get(1);
			double totalPrice = 0;

			Optional<Hotel> sortedHotelList = hotelList.stream()
					.max(Comparator.comparing(Hotel::getRating));

			if(customerType.equalsIgnoreCase("Regular")) {

				totalPrice = weekdaysNumber * sortedHotelList.get().getWeekdayRegularCustomerCost()
						+ weekendsNumber * sortedHotelList.get().getWeekendRegularCustomerCost();
			}
			else if(customerType.equalsIgnoreCase("Reward")){

				totalPrice = weekdaysNumber * sortedHotelList.get().getWeekdayRewardCustomerCost()
						+ weekendsNumber * sortedHotelList.get().getWeekendRewardCustomerCost();
			}


			System.out.println("Best Rated Hotel : \n" + sortedHotelList.get().getHotelName() + ", Rating : "
					+ sortedHotelList.get().getRating() + ", Total Rates: " + totalPrice);
			return sortedHotelList.get();
		}
		catch(NullPointerException e) {
			throw new HotelReservationException(HotelReservationException.ExceptionType.ENTERED_NULL, "NULL Value Entered");
		}

	}

}