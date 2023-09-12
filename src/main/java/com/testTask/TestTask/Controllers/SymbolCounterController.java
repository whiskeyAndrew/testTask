package com.testTask.TestTask.Controllers;

import com.testTask.TestTask.Services.SymbolCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/counter")
@RequiredArgsConstructor
public class SymbolCounterController {
    private final SymbolCounterService symbolCounterService;

    @GetMapping("/chars")
    public ResponseEntity<Map<Character, Integer>> getCharactersCountInMessage(@RequestBody String row) {
        Map<Character, Integer> result = symbolCounterService.getCharactersCountFromStringRow(row);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}


