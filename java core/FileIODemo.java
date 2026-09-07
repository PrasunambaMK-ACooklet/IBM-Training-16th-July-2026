package com.bank.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * TOPIC: IO Streams (File IO)
 *
 * Shows both the classic java.io (character streams with try-with-resources)
 * and the modern java.nio.file convenience API for writing/reading a
 * transaction log file.
 */
public class FileIODemo {

    public static void main(String[] args) throws IOException {
        String logFileName = "transaction_log.txt";

        // ---- Writing with classic java.io, try-with-resources auto-closes the writer ----
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName))) {
            writer.write("2024-01-05,DEPOSIT,5000.00,SB-1001");
            writer.newLine();
            writer.write("2024-01-06,WITHDRAWAL,1200.00,SB-1001");
            writer.newLine();
            writer.write("2024-01-07,TRANSFER_OUT,2500.00,SB-1001");
            writer.newLine();
        }
        System.out.println("Wrote transaction log using BufferedWriter/FileWriter.");

        // ---- Reading it back line by line, classic java.io ----
        try (BufferedReader reader = new BufferedReader(new FileReader(logFileName))) {
            String line;
            System.out.println("--- Reading back with BufferedReader ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // ---- Modern java.nio.file convenience methods ----
        Path logPath = Path.of(logFileName);
        List<String> allLines = Files.readAllLines(logPath);
        System.out.println("--- Reading back with Files.readAllLines (NIO) ---");
        allLines.forEach(System.out::println);

        Files.writeString(logPath, "2024-01-08,INTEREST_CREDIT,45.20,SB-1001" + System.lineSeparator(),
                java.nio.file.StandardOpenOption.APPEND);
        System.out.println("Appended one more line using Files.writeString (NIO).");

        System.out.println("Total lines now: " + Files.readAllLines(logPath).size());
    }
}
