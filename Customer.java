package com.lab11;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Customer {
    private static final Logger logger = LogManager.getLogger(Customer.class);
    private String name;
    private String email;
    // БАГИЙН АЛДААТАЙ ХУВИЛБАР - NullPointerException үүсгэдэг
    public Customer(String name, String email) {
        logger.trace("Entering Customer constructor with name={}, email={}", name, email);
        this.name = name;
        this.email = email;
        logger.debug("Customer created: name={}, email={}", this.name, this.email);
        logger.trace("Exiting Customer constructor");
    }
    // ЭНД АЛДАА БАЙНА: email null үед NullPointerException үүснэ
    public String getDomain() {
        logger.trace("Entering getDomain() for customer: {}", name);
        logger.debug("getDomain called with email={}", email);
        // Алдаа засахаас өмнө: email null байж болзошгүй
        // Алдаатай мөр:
        // return email.substring(email.indexOf("@") + 1).toUpperCase();
        // АЛДАА ЗАССАН ХУВИЛБАР:
        if (email == null) {
            logger.warn("Email is null for customer: {}. Returning 'UNKNOWN'", name);
            logger.trace("Exiting getDomain() - email is null");
            return "UNKNOWN";
        } 
        if (!email.contains("@")) {
            logger.warn("Invalid email format (no @ symbol) for customer: {}, email: {}", name, email);
            logger.trace("Exiting getDomain() - invalid email format");
            return "INVALID";
        }
        String domain = email.substring(email.indexOf("@") + 1).toUpperCase();
        logger.debug("Extracted domain: {} for customer: {}", domain, name);
        logger.trace("Exiting getDomain()");
        return domain;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        logger.debug("Updating email for {} from {} to {}", name, this.email, email);
        this.email = email;
    }
}
