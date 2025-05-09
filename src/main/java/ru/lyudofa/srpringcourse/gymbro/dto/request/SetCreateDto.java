package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record SetCreateDto(
        @NotNull
        @Positive(message = "Номер подхода должен быть положительным")
        Long setNumber,

        @NotNull
        @Positive(message = "Количество повторений должно быть положительным")
        Long reps,

        @PositiveOrZero(message = "Вес не может быть отрицательным")
        @Digits(integer = 3, fraction = 2, message = "Некорректный формат веса")
        Double weightKg,

        @Min(1)
        @Max(10)
        @Digits(integer = 1, fraction = 1)
        Double rpe,

        @Size(max = 1000, message = "Максимум 1000 символов")
        String notes
) {}
