package top.shusheng007.composite.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TargetManValidator implements ConstraintValidator<TargetMan, String> {

    private List<String> values = new ArrayList<>();

    @Override
    public void initialize(TargetMan constraintAnnotation) {
        values = Arrays.stream(constraintAnnotation.value()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return values.contains(value);
    }
}
