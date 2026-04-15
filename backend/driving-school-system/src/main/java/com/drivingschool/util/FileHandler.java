package com.drivingschool.util;

import com.drivingschool.entity.Admin;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "data/admins.txt";

    public static List<Admin> readAllAdmins() {

        List<Admin> admins = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return admins;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    admins.add(Admin.fromFileString(line));
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return admins;
    }

    public static void writeAllAdmins(List<Admin> admins) {

        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));

            for (Admin admin : admins) {
                writer.write(admin.toFileString());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}