package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private DatabaseConfig() {
    }

    // Static initializer: baca dari file .env dulu, fallback ke System.getenv()
    static {
        bacaFileEnv();
        if (URL == null) {
            URL = System.getenv("DB_URL");
        }
        if (USER == null) {
            USER = System.getenv("DB_USER");
        }
        if (PASSWORD == null) {
            PASSWORD = System.getenv("DB_PASSWORD");
        }
    }

    private static void bacaFileEnv() {
        String envPath = ".env";
        try (BufferedReader reader = new BufferedReader(new FileReader(envPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "DB_URL":
                            URL = value;
                            break;
                        case "DB_USER":
                            USER = value;
                            break;
                        case "DB_PASSWORD":
                            PASSWORD = value;
                            break;
                    }
                }
            }
        } catch (IOException e) {
            // File .env tidak ditemukan — nanti fallback ke System.getenv()
        }
    }

    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null) {
            throw new SQLException(
                "Kredensial database tidak ditemukan. " +
                "Buat file .env (copy dari .env.example) atau set environment variable DB_URL, DB_USER, DB_PASSWORD."
            );
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
