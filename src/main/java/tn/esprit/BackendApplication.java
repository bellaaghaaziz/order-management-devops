package tn.esprit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.utils.DataBase;

public class BackendApplication {

    private static final Logger logger = LogManager.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        try {
            logger.info("Initializing database tables for backend...");
            DataBase.initTables();
            logger.info("Database initialized successfully. Backend is ready.");
        } catch (Exception e) {
            logger.error("Backend failed to start", e);
        }
    }
}

