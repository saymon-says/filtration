package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.TestUtils.TWO_DAYS_FROM_NOW;
import static com.gridnine.testing.TestUtils.createTestFlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsNot.not;

class DepartureBeforeNowFilterTest {

    static List<Flight> createTestFlights() {
        return Arrays.asList(
                //A flight departing in the past
                createTestFlight(TWO_DAYS_FROM_NOW.minusDays(3), TWO_DAYS_FROM_NOW.plusHours(2)),
                //A normal multi segment flight
                createTestFlight(TWO_DAYS_FROM_NOW, TWO_DAYS_FROM_NOW.plusHours(2),
                        TWO_DAYS_FROM_NOW.plusHours(3), TWO_DAYS_FROM_NOW.plusHours(5)),
                //A flight departing in the past
                createTestFlight(TWO_DAYS_FROM_NOW.minusDays(5), TWO_DAYS_FROM_NOW),
                //A flight that arrives before it departing
                createTestFlight(TWO_DAYS_FROM_NOW, TWO_DAYS_FROM_NOW.minusHours(6)),
                //A flight with more than two hours ground time
                createTestFlight(TWO_DAYS_FROM_NOW, TWO_DAYS_FROM_NOW.plusHours(2),
                        TWO_DAYS_FROM_NOW.plusHours(5), TWO_DAYS_FROM_NOW.plusHours(6)),
                //Another flight with more than two hours ground time
                createTestFlight(TWO_DAYS_FROM_NOW, TWO_DAYS_FROM_NOW.plusHours(2),
                        TWO_DAYS_FROM_NOW.plusHours(3), TWO_DAYS_FROM_NOW.plusHours(4),
                        TWO_DAYS_FROM_NOW.plusHours(6), TWO_DAYS_FROM_NOW.plusHours(7)));
    }

    @Test
    void filterFlights() {
        List<Flight> flights = createTestFlights();
        assertThat(flights, hasSize(6));
        List<Flight> filteredFlights = new DepartureBeforeNowFilter().filterFlights(flights);
        assertThat(filteredFlights, hasSize(4));
        assertThat(filteredFlights,
                not(hasItem(createTestFlight(TWO_DAYS_FROM_NOW.minusDays(3), TWO_DAYS_FROM_NOW.plusHours(2)))));
        assertThat(filteredFlights,
                not(hasItem(createTestFlight(TWO_DAYS_FROM_NOW.minusDays(5), TWO_DAYS_FROM_NOW))));
    }
}