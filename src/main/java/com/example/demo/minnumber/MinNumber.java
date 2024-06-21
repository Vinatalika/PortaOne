package com.example.demo.minnumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MinNumber {

    public int findMinNumber(String filePath) throws IOException {
        int minNumber = Integer.MAX_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line);
                    if (num < minNumber) {
                        minNumber = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Не вдалося перетворити рядок на число: " + line);
                }
            }
        }

        return minNumber;
    }

}
