package com.devh.auth.configuration;

import com.devh.common.util.AES256Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Properties;

public class EnvironmentPostProcessorImpl implements EnvironmentPostProcessor {

    private AES256Utils aes256Utils;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            aes256Utils = new AES256Utils();
            String decryptedDatabasePassword = decryptDatabasePassword(environment.getProperty("aes.key"), environment.getProperty("spring.datasource.password"));
            Properties decryptedProps = new Properties();
            decryptedProps.put("spring.datasource.password", decryptedDatabasePassword);
            environment.getPropertySources().addFirst(new PropertiesPropertySource("decrypted", decryptedProps));
            databaseConnectionTest(
                    environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to start post process ... - " + e.getMessage());
            System.exit(0);
        }

    }

    private String decryptDatabasePassword(String key, String enc) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return aes256Utils.decrypt(key, enc);
    }

    private void databaseConnectionTest(String url, String username, String password) {
        System.out.println(password);
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
