package Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Management.AppConfig;
import Voter.Voter;

public class Helper {
    public static synchronized void syncPrint(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your requirements
        }

        // remove header row
        lines.remove(0);

        return lines;
    }

    public static List<String> getIDList(String filePath) {
        List<String> IDs = readLinesFromFile(filePath);
        return IDs;
    }

    public static List<Voter> getVotersList(String filePath) {
        List<String> lines = readLinesFromFile(filePath);
        List<Voter> voters = new ArrayList<>();

        for (String line : lines) {
            // split columns by TAB character
            String[] parts = line.split("\t");

            // Assuming the format is consistent and each line has all required fields
            String firstName = parts[0];
            String lastName = parts[1];
            String id = parts[2];
            int age = Integer.parseInt(parts[3]);
            String mayorSelection = parts[4];
            String listSelection = parts[5];
            int arrivalTime = Integer.parseInt(parts[6]);

            // Create a new Voter object and add it to the list
            Voter voter = new Voter(firstName, lastName, id, age, mayorSelection, listSelection, arrivalTime);
            voters.add(voter);
        }
        return voters;
    }

    public static void waitForObject(Object object) {
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }

    public static Boolean setSecurityGuardNumber(Integer securityGuardNumber) {
        if (securityGuardNumber >= 1 && securityGuardNumber <= 4) {
            AppConfig.securityGuardNumber = securityGuardNumber;
            return true;
        }
        return false;
    }

    public static Boolean setTimeUntilClosingNumber(Double timeUntilClosingNumber) {
        if (timeUntilClosingNumber >= 0.0 && timeUntilClosingNumber <= 24.0) {
            AppConfig.timeUntilClosingNumber = timeUntilClosingNumber;
            return true;
        }
        return false;
    }
}