package lab01.tdd;

import lab01.tdd.CircularList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CircularListImpl implements CircularList {

    private ArrayList<Integer> list;
    private int currentIndex;

    public CircularListImpl() {
        list = new ArrayList<>();
        currentIndex = 0;
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if(list.isEmpty()) {
            return Optional.empty();
        } else {
            Optional<Integer> result;
            result = Optional.of(list.get(currentIndex));
            if(currentIndex <= list.size()) {
                currentIndex++;
            } else {
                currentIndex = 0;
            }
            return result;
        }
    }

    @Override
    public Optional<Integer> previous() {
        if(this.isEmpty())
            return Optional.empty();
        if(currentIndex == 0) {
            currentIndex = this.size() - 1;
            return Optional.of(list.get(currentIndex));
        }
        currentIndex--;
        return Optional.of(list.get(currentIndex));
    }

    @Override
    public void reset() {
        this.list = new ArrayList<>();
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        OptionalInt nextElement = this.findFirstOccurrenceFromStrategy(strategy);
        if(nextElement.isEmpty())
            return Optional.empty();
        else {
            currentIndex = this.findFirstOccurrenceIndex(nextElement.getAsInt()) + 1;
            System.out.println(currentIndex);
            return Optional.of(nextElement.getAsInt());
        }
    }

    private OptionalInt findFirstOccurrenceFromStrategy(SelectStrategy strategy) {
        return IntStream.range(currentIndex, list.size()).filter(i -> strategy.apply(list.get(i))).map(i -> list.get(i)).findFirst();
    }

    private int findFirstOccurrenceIndex(int nextElement) {
        return IntStream.range(currentIndex, list.size()).filter(i -> list.get(i) == (nextElement)).findFirst().getAsInt();
    }
}
