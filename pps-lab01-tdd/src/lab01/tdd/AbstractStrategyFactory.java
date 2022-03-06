package lab01.tdd;

public interface AbstractStrategyFactory {

    SelectStrategy evenStrategy();

    SelectStrategy oddStrategy();

    SelectStrategy multipleNumberStrategy(int element);

    SelectStrategy equalsStrategy(int element);

}
