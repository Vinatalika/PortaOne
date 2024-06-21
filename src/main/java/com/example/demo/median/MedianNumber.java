package com.example.demo.median;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianNumber {

    public double findMedianNumber(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line);
                    numbers.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("Не вдалося перетворити рядок на число: " + line);
                }
            }
        }

        Collections.sort(numbers);

        int size = numbers.size();
        if (size == 0) {
            throw new IllegalArgumentException("Файл не містить чисел.");
        } else if (size % 2 == 0) {
            return 0.5 * (numbers.get(size / 2 - 1) + numbers.get(size / 2));
        } else {
            return numbers.get(size / 2);
        }
    }

}
