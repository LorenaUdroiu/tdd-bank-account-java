package org.xpdojo.bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Account {
    private int balance = 0;

    public int balance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }

        if (this.balance() < amount) {
            throw new RuntimeException("Not enough money in your account!");
        }

        balance -= amount;
    }

    public void transfer(Account account, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }

        if (this.balance() < amount) {
            throw new RuntimeException("Not enough money in your account!");
        }

        this.withdraw(amount);
        account.deposit(amount);
    }

    public BalanceSlip printBalanceSlip(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        BalanceSlip balanceSlip = new BalanceSlip();
        balanceSlip.setDate(localDate.toString());
        balanceSlip.setTime(localTime.toString());
        balanceSlip.setBalance(balance());

        return balanceSlip;
    }
}
