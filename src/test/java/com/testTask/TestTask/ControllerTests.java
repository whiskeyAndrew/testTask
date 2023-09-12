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
public class ControllerTests {

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
    void controllerStatusTest(){
        ResponseEntity<Map<Character,Integer>> expectedResponse = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(expectedResponse,counterController.getCharactersCountInMessage(""));
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
