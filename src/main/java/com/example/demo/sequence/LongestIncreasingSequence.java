package com.example.demo.sequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSequence {

    public List<Integer> findLongestIncreasingSequence(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> longestSequence = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();

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

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Файл не містить чисел.");
        }

        currentSequence.add(numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                currentSequence.add(numbers.get(i));
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
                currentSequence.add(numbers.get(i));
            }
        }
        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = new ArrayList<>(currentSequence);
        }

        return longestSequence;
    }

}
