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
}
