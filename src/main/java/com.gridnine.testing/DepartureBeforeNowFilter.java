package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartureBeforeNowFilter implements Filter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> resultList = new ArrayList<>(List.copyOf(flights));
        for (Flight flight : flights) {
            if (flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())) {
                resultList.remove(flight);
            }
        }
        return resultList;
    }
}
