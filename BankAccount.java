package com.lab11;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BankAccount {
    private static final Logger logger = LogManager.getLogger(BankAccount.class);
    private double balance;
    public BankAccount() {
        this.balance = 0.0;
        logger.trace("BankAccount instance created with initial balance: {}", balance);
    }
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            logger.fatal("Cannot create account with negative initial balance: {}", initialBalance);
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
        logger.info("BankAccount created with initial balance: {}", balance);
    }
    public void deposit(double amount) {
        logger.trace("Entering deposit() with amount={}", amount);
        if (amount < 0) {
            logger.warn("Invalid input: amount {} is negative. Deposit operation cancelled.", amount);
            logger.trace("Exiting deposit() - invalid input");
            return;
        }
        if (amount == 0) {
            logger.warn("Deposit amount is zero. No change to balance.");
            logger.trace("Exiting deposit() - zero amount");
            return;
        }
        logger.debug("Balance before deposit: {}", balance);
        double previousBalance = balance;
        balance += amount;
        logger.info("Deposited {}. Balance changed from {} to {}", amount, previousBalance, balance);
        logger.trace("Exiting deposit()");
    }
    public void withdraw(double amount) {
        logger.trace("Entering withdraw() with amount={}", amount);        
        if (amount < 0) {
            logger.warn("Invalid input: withdrawal amount {} is negative. Operation cancelled.", amount);
            logger.trace("Exiting withdraw() - invalid input");
            return;
        }
        if (amount == 0) {
            logger.warn("Withdrawal amount is zero. No change to balance.");
            logger.trace("Exiting withdraw() - zero amount");
            return;
        }
        logger.debug("Balance before withdrawal: {}", balance);
        if (amount > balance) {
            logger.error("Insufficient funds. Requested: {}, Available: {}", amount, balance);
            logger.trace("Exiting withdraw() - insufficient funds");
            return;
        }
        double previousBalance = balance;
        balance -= amount;
        logger.info("Withdrew {}. Balance changed from {} to {}", amount, previousBalance, balance);
        logger.trace("Exiting withdraw()");
    }
    public double getBalance() {
        logger.trace("Entering getBalance()");
        logger.debug("Returning current balance: {}", balance);
        logger.trace("Exiting getBalance()");
        return balance;
    }
}
