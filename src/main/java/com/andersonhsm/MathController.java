package com.andersonhsm;

import com.andersonhsm.dto.body.AverageRequestBody;
import com.andersonhsm.exceptions.UnsupportedMathOperationException;
import com.andersonhsm.predicates.IsNumericPredicate;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private static final String template = "Hello, %s!";
    private static AtomicLong counter = new AtomicLong();


    @GetMapping("sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(name = "numberOne", required = true) String numberOne,
            @PathVariable(name = "numberTwo", required = true) String numberTwo) throws Exception {


        if (!IsNumericPredicate.isNumeric(numberOne) || !IsNumericPredicate.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Non numeric parameter provided");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping("subtract/{numberOne}/{numberTwo}")
    public Double subtract(
            @PathVariable(name = "numberOne", required = true) String numberOne,
            @PathVariable(name = "numberTwo", required = true) String numberTwo) throws Exception {


        if (!IsNumericPredicate.isNumeric(numberOne) || !IsNumericPredicate.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Non numeric parameter provided");
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping("multiply/{numberOne}/{numberTwo}")
    public Double multiply(
            @PathVariable(name = "numberOne", required = true) String numberOne,
            @PathVariable(name = "numberTwo", required = true) String numberTwo) throws Exception {


        if (!IsNumericPredicate.isNumeric(numberOne) || !IsNumericPredicate.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Non numeric parameter provided");
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping("divide/{numberOne}/{numberTwo}")
    public Double divide(
            @PathVariable(name = "numberOne", required = true) String numberOne,
            @PathVariable(name = "numberTwo", required = true) String numberTwo) throws Exception {

        if (convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("It's not possible to divide by zero");
        }

        if (!IsNumericPredicate.isNumeric(numberOne) || !IsNumericPredicate.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Non numeric parameter provided");
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping("toSquare/{numberOne}")
    public Double toSquare(
            @PathVariable(name = "number", required = true) String number) {


        if (!IsNumericPredicate.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Non numeric parameter provided");
        }

        return Math.sqrt(convertToDouble(number));
    }

    @PostMapping("average")
    public Double average(
            @RequestBody(required = true) AverageRequestBody body) {
        List<String> numbers = body.getNumbers();

        if (!numbers.stream().allMatch(new IsNumericPredicate())) {
            throw new UnsupportedMathOperationException("A non numeric value was provided");
        }

        return numbers.stream().map(this::convertToDouble).reduce(0.0, (subtotal, element) -> subtotal + element) / numbers.size();
    }

    private Double convertToDouble(String stringNumber) {
        if (stringNumber == null) return 0D;

        String normalizedString = stringNumber.replaceAll(",", ".");
        return IsNumericPredicate.isNumeric(normalizedString) ? Double.parseDouble(normalizedString) : 0D;
    }

}
