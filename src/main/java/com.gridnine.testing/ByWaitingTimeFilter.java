package com.gridnine.testing;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ByWaitingTimeFilter implements Filter {

    private static final int WAITING_HOURS = 2;

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> resultList = new ArrayList<>(List.copyOf(flights));
        for (Flight flight : flights) {
            if (isLongerAwaiting(flight)) {
                resultList.remove(flight);
            }
        }
        return resultList;
    }

    private boolean isLongerAwaiting(Flight flight) {
        final List<Segment> segments = flight.getSegments();
        long waiting = 0;
        for (int i = 1; i < segments.size(); i++) {
            var arrival = segments.get(i - 1).getArrivalDate();
            var departure = segments.get(i).getDepartureDate();
            waiting += ChronoUnit.HOURS.between(arrival, departure);
        }
        return waiting > WAITING_HOURS;
    }
}
