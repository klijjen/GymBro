package ru.lyudofa.srpringcourse.gymbro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lyudofa.srpringcourse.gymbro.model.Exercise;
import ru.lyudofa.srpringcourse.gymbro.model.WorkoutExercise;
import ru.lyudofa.srpringcourse.gymbro.repositories.WorkoutExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkoutExerciseService {
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public WorkoutExercise addExerciseToWorkout(WorkoutExercise workoutExercise) {
        return workoutExerciseRepository.save(workoutExercise);
    }

    public Optional<WorkoutExercise> findById(Long id) {
        return workoutExerciseRepository.findById(id);
    }

    public List<WorkoutExercise> findByWorkoutId(Long workoutId) {
        return workoutExerciseRepository.findByWorkoutId(workoutId);
    }

    public Optional<WorkoutExercise> findByIdAndWorkoutId(Long id, Long workoutId) {
        return workoutExerciseRepository.findByIdAndWorkoutId(id, workoutId);
    }

    public boolean existsByWorkoutIdAndExerciseId(Long workoutId, Long exerciseId) {
        return workoutExerciseRepository.existsByWorkoutIdAndExerciseId(workoutId, exerciseId);
    }

    public List<Exercise> findByMuscleGroupContainingIgnoreCase(String muscleGroup) {
        return workoutExerciseRepository.findByMuscleGroupContainingIgnoreCase(muscleGroup);
    }

    public List<Exercise> findByCreatorId(Long userId) {
        return workoutExerciseRepository.findByCreatorId(userId);
    }

    public void deleteWorkoutExercise(Long id) {
        if (!workoutExerciseRepository.existsById(id)) {
            throw new IllegalArgumentException("WorkoutExercise not found");
        }
        workoutExerciseRepository.deleteById(id);
    }
}
