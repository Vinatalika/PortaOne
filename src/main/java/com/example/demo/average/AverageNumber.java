package com.example.demo.average;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AverageNumber {

    public double findAverageNumber(String filePath) throws IOException {
        long sum = 0;
        long count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line);
                    sum += num;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Не вдалося перетворити рядок на число: " + line);
                }
            }
        }

        return count > 0 ? (double) sum / count : 0;
    }
}
