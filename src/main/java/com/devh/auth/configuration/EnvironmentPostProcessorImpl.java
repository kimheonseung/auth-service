package com.devh.auth.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class EnvironmentPostProcessorImpl implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            databaseConnectionTest(
                    environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));
        } catch (Exception e) {
            System.out.println("Failed to start post process ... - " + e.getMessage());
            System.exit(0);
        }

    }

    private void databaseConnectionTest(String url, String username, String password) {
        boolean isConnected = false;
        while(!isConnected) {
            try (
                    Connection conn = DriverManager.getConnection(url, username, password)
                    ) {
                DatabaseMetaData metadata = conn.getMetaData();
                System.out.printf("%s - %d.%d [%s - %s]%n",
                        metadata.getDatabaseProductName(),
                        metadata.getDatabaseMajorVersion(),
                        metadata.getDatabaseMinorVersion(),
                        metadata.getDriverName(),
                        metadata.getDriverVersion()
                );
                isConnected = true;
                System.out.println("Success to test database connection !");
            } catch (Exception e) {
                System.out.println("Failed to test database connection ... - " + e.getMessage());
                try { Thread.sleep(3000L); } catch (InterruptedException ignored) {}
            }
        }
    }
}
