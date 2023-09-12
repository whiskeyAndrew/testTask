package com.testTask.TestTask;

import com.testTask.TestTask.Controllers.SymbolCounterController;
import com.testTask.TestTask.Services.SymbolCounterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
class TestTaskApplicationTests {
    private SymbolCounterService scService;
    private SymbolCounterController counterController;
    @BeforeEach
    void init() {
        scService = new SymbolCounterService();
        counterController = new SymbolCounterController(scService);
    }

    @Test
    void controllerTest(){
        Map<Character, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put('q',5);
        expectedMap.put('w',4);
        ResponseEntity<Map<Character,Integer>> expectedResponse = new ResponseEntity<>(expectedMap, HttpStatus.OK);
        ResponseEntity<Map<Character,Integer>> actual = counterController.getCharactersCountInMessage("qqqqqwwww");
        Assertions.assertEquals(expectedResponse,actual);
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
    @Test
    void onNullTest(){
        Assertions.assertNull(counterController.getCharactersCountInMessage(null).getBody());
    }
    @Test
    void onEmptyTest(){
        Assertions.assertNull(counterController.getCharactersCountInMessage("").getBody());
    }
}
