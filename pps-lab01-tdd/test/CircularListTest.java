import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    public static final int MAX_ELEMENTS = 20;
    public static final int START_INCLUSIVE = 1;
    public static final int LAST_VALUE = 19;
    public static final int SECOND_LAST_VALUE = 18;
    public static final int THIRD_VALUE = 3;
    private CircularList list;

    @BeforeEach
    public void init() {
        list = new CircularListImpl();
        IntStream.range(START_INCLUSIVE, MAX_ELEMENTS).forEach(i -> list.add(i));
    }

    @Test
    public void testSimpleAddFinalSize() {
        int sizeBeforeAdd = list.size();
        int sizeAfterAdd;
        list.add(1);
        sizeAfterAdd = list.size();
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

    @Test
    public void testSimpleAdd() {
        int randomValue = new Random().nextInt();
        list.add(randomValue);
        assertEquals(randomValue, list.previous().get());
    }

    @Test
    public void hasPrevious() {
        assertTrue(list.previous().isPresent());
    }

    @Test
    public void testPreviousElementFromStart() {
        assertTrue(list.previous().get() == LAST_VALUE);
    }

    @Test
    public void testPreviousElement() {
        list.previous();
        assertTrue(list.previous().get() == SECOND_LAST_VALUE);
    }

    @Test
    public void testNextElementFromStart() {
        assertTrue(list.next().get() == START_INCLUSIVE);
    }

    @Test
    public void testNextElement() {
        list.next();
        list.next();
        assertTrue(list.next().get() == THIRD_VALUE);
    }

    @Test
    public void testNextAndPrevious() {
        list.next();
        assertEquals(1, list.previous().get());
    }

    @Test
    public void testPreviousAndNext() {
        list.previous();
        assertEquals(LAST_VALUE, list.next().get());
    }

    @Test
    public void testReset() {
        list.reset();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testResetAndNext() {
        list.reset();
        assertTrue(list.next().isEmpty());
    }

    @Test
    public void testSize() {
        list.add(4);
        assertEquals(MAX_ELEMENTS, list.size());
    }

    @Test
    public void testEvenNumberStrategy() {
        assertEquals(2, list.next((i) -> i % 2 == 0).get());
    }

    @Test
    public void testOddNumberStrategy() {
        assertEquals(1, list.next((i) -> i % 2 != 0).get());
    }

    @Test
    public void testEqualNumberStrategy() {
        assertEquals(2, list.next((i) -> i == 2).get());
    }

    @Test
    public void testMultipleNumberStrategy() {
        int factor = 6;
        assertEquals(factor, list.next((i) -> i % factor == 0).get());
        assertEquals(factor * 2, list.next((i) -> i % factor == 0).get());
    }

    @Disabled
    @Test public void testTodo(){
        Assertions.fail();
    }

}
