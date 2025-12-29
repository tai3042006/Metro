public interface BusPathService {

    JourneyPlan findBusJourney(Station start, Station end);

    JourneyPlan findBusMetroBusJourney(Station start, Station end);
}
