import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;
import org.junit.jupiter.api.*;

import java.util.function.IntSupplier;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBankAccountTest {

    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;
    protected IntSupplier fee;

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    public void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), 100);
        assertEquals(100 - fee.getAsInt(), bankAccount.getBalance());
    }

    @Test
    public void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(accountHolder.getId(), 70);
        assertEquals(30 - (2 * fee.getAsInt()), bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.deposit(2, 50);
        assertEquals(100 - fee.getAsInt(), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(2, 70);
        assertEquals(100 - fee.getAsInt(), bankAccount.getBalance());
    }
}
