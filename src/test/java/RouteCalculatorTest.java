import core.Line;
import core.Station;
import junit.framework.TestCase;


import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    RouteCalculator calculator;
    List<Station> route;
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        route = new ArrayList<>();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(new Station("Петровская", line1));
        stationIndex.addStation(new Station("Арбузная", line1));
        stationIndex.addStation(new Station("Морковная", line2));
        stationIndex.addStation(new Station("Яблочная", line2));
        stationIndex.addStation(new Station("Колхозная", line3));
        stationIndex.addStation(new Station("Черниговская", line3));

        stationIndex.addConnection(line1.getStations());

        route.add(stationIndex.getStation("Петровская"));
        route.add(stationIndex.getStation("Арбузная"));
        route.add(stationIndex.getStation("Морковная"));
        route.add(stationIndex.getStation("Яблочная"));
        route.add(stationIndex.getStation("Колхозная"));
        route.add(stationIndex.getStation("Черниговская"));
    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 14.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute(){
        calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(route.get(0), route.get(5));
        List<Station> expected = route;
        assertEquals(expected, actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
