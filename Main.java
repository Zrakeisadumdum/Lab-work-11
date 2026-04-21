package com.lab11;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("========== APPLICATION STARTED ==========");
        logger.info("Log4j 2 logging demo starting...");
        testBankAccount();
        testCustomer();
        demonstrateLogLevels();
        logger.info("========== APPLICATION FINISHED ==========");
    }
    private static void testBankAccount() {
        logger.info("--- Testing BankAccount ---");
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        account.withdraw(300);
        logger.info("Current balance: {}", account.getBalance());
        account.deposit(-100);
        account.withdraw(-50);
        account.withdraw(2000);
        account.deposit(0);
        account.withdraw(0);
        try {
            BankAccount invalidAccount = new BankAccount(-500);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to create account with negative balance: {}", e.getMessage());
        }
    }
    private static void testCustomer() {
        logger.info("--- Testing Customer with logging ---");
        Customer customer1 = new Customer("Батболд", "batbold@must.edu.mn");
        String domain1 = customer1.getDomain();
        logger.info("Customer {} domain: {}", customer1.getName(), domain1);
        logger.warn("Testing customer with null email - this would cause NPE in buggy version");
        Customer customer2 = new Customer("Тэст", null);     
        String domain2 = customer2.getDomain();
        logger.info("Customer {} domain: {}", customer2.getName(), domain2);
        customer2.setEmail("test@example.com");
        String domain3 = customer2.getDomain();
        logger.info("Customer {} updated domain: {}", customer2.getName(), domain3);
        Customer customer3 = new Customer("Буруу", "invalidemail.com");
        String domain4 = customer3.getDomain();
        logger.info("Customer {} domain: {}", customer3.getName(), domain4);
    }
    private static void demonstrateLogLevels() {
        logger.info("--- Demonstrating all 6 log levels ---");
        logger.trace("This is a TRACE message - most detailed, method entry/exit");
        logger.debug("This is a DEBUG message - variable values, intermediate results");
        logger.info("This is an INFO message - normal execution flow");
        logger.warn("This is a WARN message - potential issues, invalid input");
        logger.error("This is an ERROR message - serious errors, exceptions");
        logger.fatal("This is a FATAL message - critical errors causing system crash");
        String userId = "user123";
        String action = "login";
        int statusCode = 200;
        logger.info("User action: userId={}, action={}, status={}", userId, action, statusCode);
    }
}
