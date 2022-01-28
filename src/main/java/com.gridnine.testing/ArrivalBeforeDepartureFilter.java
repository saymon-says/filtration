package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArrivalBeforeDepartureFilter implements Filter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> resultList = new ArrayList<>(List.copyOf(flights));
        for (Flight flight : flights) {
            final List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                LocalDateTime departureDateTime = segment.getDepartureDate();
                LocalDateTime arrivalDateTime = segment.getArrivalDate();
                if (departureDateTime.isAfter(arrivalDateTime)) {
                    resultList.remove(flight);
                }
            }
        }
        return resultList;
    }
}
