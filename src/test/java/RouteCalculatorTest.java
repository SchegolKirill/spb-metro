import core.Line;
import core.Station;
import junit.framework.TestCase;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class RouteCalculatorTest extends TestCase {
    RouteCalculator calculator;
    List<Station> route;
    StationIndex stationIndex;
    TreeMap<Station, TreeSet<Station>> myCustomConnections;
    TreeSet<Station> stations;

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

        Station st1 = new Station("Петровская", line1);
        Station st2 = new Station("Арбузная", line1);
        Station st3 = new Station("Морковная", line2);
        Station st4 = new Station("Яблочная", line2);
        Station st5 = new Station("Колхозная", line3);
        Station st6 = new Station("Черниговская", line3);

        line1.addStation(st1);
        line1.addStation(st2);
        line2.addStation(st3);
        line2.addStation(st4);
        line3.addStation(st5);
        line3.addStation(st6);

        stationIndex.addStation(st1);
        stationIndex.addStation(st2);
        stationIndex.addStation(st3);
        stationIndex.addStation(st4);
        stationIndex.addStation(st5);
        stationIndex.addStation(st6);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        myCustomConnections = new TreeMap<>();
        TreeSet<Station> p1 = new TreeSet<>();
        p1.add(st3);
        myCustomConnections.put(st2,p1);
        TreeSet<Station> p2 = new TreeSet<>();
        p2.add(st5);
        myCustomConnections.put(st4,p2);
        TreeSet<Station> p3 = new TreeSet<>();
        p3.add(st2);
        myCustomConnections.put(st3,p3);
        TreeSet<Station> p4 = new TreeSet<>();
        p4.add(st4);
        myCustomConnections.put(st5,p4);

        stationIndex.connections.putAll(myCustomConnections);

        route.add(st1);
        route.add(st2);
        route.add(st3);
        route.add(st4);
        route.add(st5);
        route.add(st6);
//        route.add(st6);
//        route.add(st5);
//        route.add(st4);
//        route.add(st3);
//        route.add(st2);
//        route.add(st1);
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
