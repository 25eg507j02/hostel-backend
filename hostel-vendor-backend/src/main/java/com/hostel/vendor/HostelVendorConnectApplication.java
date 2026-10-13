package com.hostel.vendor;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HostelVendorConnectApplication {

    public static void main(String[] args) {
        // Load environment variables from .env file if present
        try {
            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

            dotenv.entries().forEach(entry -> {
                if (System.getProperty(entry.getKey()) == null) {
                    System.setProperty(entry.getKey(), entry.getValue());
                }
            });
        } catch (Exception e) {
            // In containerized/cloud deployment (e.g. Render, Railway, AWS), system environment variables are used directly
            System.out.println("Notice: .env file not found or skipped. Utilizing system environment variables.");
        }

        SpringApplication.run(HostelVendorConnectApplication.class, args);
    }
}
