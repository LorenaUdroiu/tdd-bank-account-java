package org.xpdojo.bank;

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
        balance -= amount;
    }
}
