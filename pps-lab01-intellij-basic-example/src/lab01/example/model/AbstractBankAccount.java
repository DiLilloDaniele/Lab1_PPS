package lab01.example.model;

public abstract class AbstractBankAccount implements BankAccount{

    protected double balance;
    protected AccountHolder holder;

    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            this.balance += amount;
            payFees();
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance -= amount;
            payFees();
        }
    }

    abstract protected int getFee();

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= (amount + getFee());
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }

    private void payFees(){
        balance -= getFee();
    }

}
