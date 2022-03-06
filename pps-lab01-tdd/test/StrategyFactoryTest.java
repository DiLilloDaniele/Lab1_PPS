import lab01.tdd.AbstractStrategyFactory;
import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import lab01.tdd.SimpleStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyFactoryTest {

    private AbstractStrategyFactory simpleStrategyFactory;
    private CircularList list;

    @BeforeEach
    public void init() {
        simpleStrategyFactory = new SimpleStrategyFactory();
        list = new CircularListImpl();
        IntStream.range(1, 20).forEach(i -> list.add(i));
    }

    @Test
    public void testEvenStrategy() {
        List expectedList = new ArrayList<>();
        IntStream.range(1, 20).filter(i -> i % 2 == 0).forEach(i -> expectedList.add(i));

        expectedList.stream().forEach(i -> {
            assertEquals(i, list.next(simpleStrategyFactory.evenStrategy()).get());
        });
    }

    @Test
    public void testOddStrategy() {
        List expectedList = new ArrayList<>();
        IntStream.range(1, 20).filter(i -> i % 2 != 0).forEach(i -> expectedList.add(i));

        expectedList.stream().forEach(i -> {
            assertEquals(i, list.next(simpleStrategyFactory.oddStrategy()).get());
        });
    }

    @Test
    public void testMultipleStrategy() {
        List expectedList = new ArrayList<>();
        int factor = 2;
        IntStream.range(1, 20).filter(i -> i % factor == 0).forEach(i -> expectedList.add(i));

        expectedList.stream().forEach(i -> {
            assertEquals(i, list.next(simpleStrategyFactory.multipleNumberStrategy(factor)).get());
        });
    }

    @Test
    public void testEqualsStrategy() {
        List expectedList = new ArrayList<>();
        int n = 2;
        IntStream.range(1, 20).filter(i -> i == n).forEach(i -> expectedList.add(i));

        expectedList.stream().forEach(i -> {
            assertEquals(i, list.next(simpleStrategyFactory.equalsStrategy(n)).get());
        });
    }

}