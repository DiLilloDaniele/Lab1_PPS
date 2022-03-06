package lab01.tdd;

import lab01.tdd.AbstractStrategyFactory;

public class SimpleStrategyFactory implements AbstractStrategyFactory {
    @Override
    public SelectStrategy evenStrategy() {
        return ((i) -> i % 2 == 0);
    }

    @Override
    public SelectStrategy oddStrategy() {
        return ((i) -> i % 2 != 0);
    }

    @Override
    public SelectStrategy multipleNumberStrategy(int element) {
        return ((i) -> i % element == 0);
    }

    @Override
    public SelectStrategy equalsStrategy(int element) {
        return ((i) -> i == element);
    }

}
