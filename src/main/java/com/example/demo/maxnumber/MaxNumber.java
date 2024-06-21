package com.example.demo.maxnumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaxNumber {

    public int findMaxNumber(String filePath) throws IOException {
        int maxNumber = Integer.MIN_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line);
                    if (num > maxNumber) {
                        maxNumber = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Не вдалося перетворити рядок на число: " + line);
                }
            }
        }

        return maxNumber;
    }
}
