package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("without flights that arrives before it departs: "
                + new ArrivalBeforeDepartureFilter().filterFlights(flights));
        System.out.println("without flights departing in the past: "
                + new DepartureBeforeNowFilter().filterFlights(flights));
        System.out.println("without flights with more than two hours ground time: "
                + new ByWaitingTimeFilter().filterFlights(flights));
    }
}
