package lab01.example.model;

public class SimpleBankAccountWithAtm extends AbstractBankAccount{

    public static final int FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    protected int getFee() {
        return FEE;
    }
}
