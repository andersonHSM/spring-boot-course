package com.andersonhsm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PersonController {

    private static final String template = "Hello, %s!";
    private static AtomicLong counter = new AtomicLong();


    @GetMapping("sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(name = "numberOne", required = true) String numberOne,
            @PathVariable(name = "numberTwo", required = true) String numberTwo) throws Exception {


        return 0D;
    }


}
