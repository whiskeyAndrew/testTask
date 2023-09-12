package com.testTask.TestTask.Services;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SymbolCounterService {
    public Map<Character, Integer> getCharactersCountFromStringRow(String row) {
        if (row == null || row.isEmpty()) {
            return null;
        }

        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : row.toCharArray()) {
            if (charFrequency.containsKey(c)) {
                int value = charFrequency.get(c);
                charFrequency.put(c, value + 1);
            } else {
                charFrequency.put(c, 1);
            }
        }

        return charFrequency.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (o1, o2) -> o1,
                        LinkedHashMap::new
                ));
    }

}
