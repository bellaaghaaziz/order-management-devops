package tn.esprit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.utils.DataBase;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Launching backend application...");

        try {
            logger.info("Initializing database tables...");
            DataBase.initTables();
            logger.info("Database tables initialized successfully.");

            // Backend is ready, you can add more backend logic here
            logger.info("Backend application started successfully.");
            Thread.currentThread().join();

        } catch (Exception e) {
            logger.error("Failed to start the backend application", e);
        }
    }
}


