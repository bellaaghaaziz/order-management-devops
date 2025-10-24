package tn.esprit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.utils.DataBase;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("🚀 Launching backend application...");

        try {
            // Initialize your database or backend components
            logger.info("Initializing database tables...");
            DataBase.initTables();
            logger.info("✅ Database tables initialized successfully.");

            // Keep the process running — so Kubernetes treats it as a service
            logger.info("Backend application is now running.");
            while (true) {
                Thread.sleep(60000); // Sleep to keep JVM alive
            }

        } catch (InterruptedException e) {
            logger.warn("Application interrupted, shutting down...");
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            logger.error("❌ Failed to start the backend application", e);
        }
    }
}



