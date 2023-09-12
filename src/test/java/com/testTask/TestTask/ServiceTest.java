package com.testTask.TestTask;

import com.testTask.TestTask.Services.SymbolCounterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceTest {
    private SymbolCounterService scService;
    @BeforeEach
    void init() {
        scService = new SymbolCounterService();
    }
    @Test
    void firstTestCharCounter() {
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('q', 5);
        expected.put('w', 5);
        expected.put('e', 4);
        expected.put('1', 3);
        expected.put('r', 3);
        expected.put('2', 3);
        expected.put('y', 3);
        expected.put('3', 2);
        expected.put('t', 1);
        Map<Character, Integer> actual = scService.getCharactersCountFromStringRow("qwerqwtqweyeqwryqewry12312312");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void secondTestCharCounter() {
        Map<Character, Integer> unexpected = new LinkedHashMap<>();
        unexpected.put('1', 2);
        unexpected.put('2', 4);
        Map<Character, Integer> actual = scService.getCharactersCountFromStringRow("12212");
        Assertions.assertNotEquals(unexpected, actual);
    }

    @Test
    void thirdTestCharCounter() {
        Map<Character, Integer> expected = new LinkedHashMap<>();
        expected.put('a', 5);
        expected.put('c', 4);
        expected.put('b', 1);

        Map<Character, Integer> actual = scService.getCharactersCountFromStringRow("aaaaabcccc");
        Assertions.assertEquals(expected, actual);
    }
}
