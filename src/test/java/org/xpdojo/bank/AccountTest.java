package org.xpdojo.bank;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void newAccountShouldHaveZeroBalance() {
        Account account = new Account();
        assertThat(account.balance()).isEqualTo(0);
    }

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalance() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);

        account.withdraw(5);
        account.withdraw(5);
        assertThat(account.balance()).isEqualTo(20);
    }

    @Test
    public void depositNegativeAmount() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);

        boolean exception = false;
        try {
            //should this be considered to be an withdraw??
            //instead of having an extra method for withdraw amount?
            account.deposit(-5);
        } catch (Exception ex) {
            exception = true;
        } finally {
            //check the balance is the same
            assertThat(account.balance()).isEqualTo(30);
        }
        assertThat(exception).isTrue();

        account.withdraw(5);
        assertThat(account.balance()).isEqualTo(25);
    }

    @Test
    public void withdrawNegativeAmount() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);

        account.withdraw(5);
        assertThat(account.balance()).isEqualTo(25);

        boolean exception = false;
        try {
            //should this be considered to be an withdraw??
            //instead of having an extra method for withdraw amount?
            account.withdraw(-5);
        } catch (Exception ex) {
            exception = true;
        } finally {
            //check the balance is the same
            assertThat(account.balance()).isEqualTo(25);
        }
        assertThat(exception).isTrue();
    }

    @Test
    public void withdrawBeyondBalance() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);

        boolean exception = false;
        try {
            //should this be considered to be an withdraw??
            //instead of having an extra method for withdraw amount?
            account.withdraw(35);
        } catch (Exception ex) {
            exception = true;
        } finally {
            //check the balance is the same
            assertThat(account.balance()).isEqualTo(30);
        }
        assertThat(exception).isTrue();
    }

    @Test
    public void transferAmountBetweenTwoAccounts() {
        Account account1 = new Account();
        account1.deposit(10);
        account1.deposit(20);
        assertThat(account1.balance()).isEqualTo(30);

        Account account2 = new Account();
        account2.deposit(10);
        account2.deposit(20);
        assertThat(account2.balance()).isEqualTo(30);

        account1.transfer(account2, 5);

        assertThat(account1.balance()).isEqualTo(25);
        assertThat(account2.balance()).isEqualTo(35);
    }

    @Test
    public void transferNegativeAmountBetweenTwoAccounts() {
        Account account1 = new Account();
        account1.deposit(10);
        account1.deposit(20);
        assertThat(account1.balance()).isEqualTo(30);

        Account account2 = new Account();
        account2.deposit(10);
        account2.deposit(20);
        assertThat(account2.balance()).isEqualTo(30);

        boolean exception = false;
        try {
            account1.transfer(account2, -5);
        } catch (Exception ex) {
            exception = true;
        } finally {
            //check the balance is the same
            assertThat(account1.balance()).isEqualTo(30);
            assertThat(account2.balance()).isEqualTo(30);
        }
    }

    @Test
    public void transferBeyondBalanceBetweenTwoAccounts() {
        Account account1 = new Account();
        account1.deposit(10);
        account1.deposit(20);
        assertThat(account1.balance()).isEqualTo(30);

        Account account2 = new Account();
        account2.deposit(10);
        account2.deposit(20);
        assertThat(account2.balance()).isEqualTo(30);

        boolean exception = false;
        try {
            account1.transfer(account2, 40);
        } catch (Exception ex) {
            exception = true;
        } finally {
            //check the balance is the same
            assertThat(account1.balance()).isEqualTo(30);
            assertThat(account2.balance()).isEqualTo(30);
        }
    }

    @Test
    public void printBalanceSlip() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);

        BalanceSlip balanceSlip = account.printBalanceSlip();

        assertThat(balanceSlip).isNotNull();
        assertThat(balanceSlip.getDate()).isNotNull();
        assertThat(balanceSlip.getTime()).isNotNull();
        assertThat(balanceSlip.getBalance()).isNotNull();

        assertThat(balanceSlip.getBalance()).isEqualTo(30);
    }
}
