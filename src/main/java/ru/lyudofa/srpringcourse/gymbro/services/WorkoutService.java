package ru.lyudofa.srpringcourse.gymbro.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.lyudofa.srpringcourse.gymbro.model.*;
import ru.lyudofa.srpringcourse.gymbro.repositories.WorkoutRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout createWorkout(User user, String name, LocalDateTime startTime, LocalDateTime endTime, String notes) {
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setName(name);
        workout.setStartTime(startTime);
        workout.setEndTime(endTime);
        workout.setNotes(notes);

        return workoutRepository.save(workout);
    }

    // Получить все тренировки пользователя
    public List<Workout> getWorkoutsByUser(User user) {
        return workoutRepository.findByUserId(user.getId());
    }

    // Получить тренировку с упражнениями
    public Optional<Workout> getWorkoutWithExercises(Long workoutId) {
        return workoutRepository.findByIdWithExercises(workoutId);
    }

    // Обновить тренировку (название, время, заметки)
    public Workout updateWorkout(Long workoutId, String name, LocalDateTime startTime, LocalDateTime endTime, String notes) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        workout.setName(name);
        workout.setStartTime(startTime);
        workout.setEndTime(endTime);
        workout.setNotes(notes);

        return workoutRepository.save(workout);
    }

    // Удалить тренировку
    public void deleteWorkout(Long workoutId) {
        if (!workoutRepository.existsById(workoutId)) {
            throw new IllegalArgumentException("Workout not found");
        }
        workoutRepository.deleteById(workoutId);
    }

}