package com.andersonhsm.dto.body;

import java.util.List;

public class AverageRequestBody {
    private List<String> numbers;

    public AverageRequestBody() {
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

}