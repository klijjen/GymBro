package ru.lyudofa.srpringcourse.gymbro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "sets")
@Getter @Setter
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "set_number", nullable = false)
    private Long setNumber;

    @Column(nullable = false)
    private Long reps;

    @Column(name = "weight_kg", precision = 5, scale = 2)
    private Double weightKg;

    @Column(precision = 3, scale = 1)
    private Double rpe;

    @Column(name = "rest_interval")
    private Duration restInterval;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;
}