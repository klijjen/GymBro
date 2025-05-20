package ru.lyudofa.srpringcourse.gymbro.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.lyudofa.srpringcourse.gymbro.model.Set;
import ru.lyudofa.srpringcourse.gymbro.model.WorkoutExercise;
import ru.lyudofa.srpringcourse.gymbro.repositories.SetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SetService {

    private final SetRepository setRepository;

    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    public Set createSet(Set set) {
        // Убедимся, что такой номер подхода не повторяется в упражнении
        boolean exists = setRepository.existsByWorkoutExercisesAndSetNumber(
                set.getWorkoutExercises(), set.getSetNumber());
        if (exists) {
            throw new IllegalArgumentException("Set number already exists in this exercise");
        }
        return setRepository.save(set);
    }

    public List<Set> getSetsByWorkoutExerciseId(Integer workoutExerciseId) {
        return setRepository.findByWorkoutExercisesId(workoutExerciseId);
    }

    public Optional<Set> findById(Long id) {
        return setRepository.findById(id);
    }

    public List<Set> getOrderedSetsByWorkoutExercise(Long workoutExerciseId) {
        return setRepository.findOrderedSetsByExercise(workoutExerciseId);
    }

    public void deleteSet(Long id) {
        if (!setRepository.existsById(id)) {
            throw new IllegalArgumentException("Set not found");
        }
        setRepository.deleteById(id);
    }

    public void deleteAllByWorkoutExercise(Long workoutExerciseId) {
        setRepository.deleteAllByWorkoutExerciseId(workoutExerciseId);
    }

    // === Обновление веса ===

    public void updateWeight(Long setId, Double newWeight) {
        if (!setRepository.existsById(setId)) {
            throw new IllegalArgumentException("Set not found");
        }
        setRepository.updateWeight(setId, newWeight);
    }

    // === Аналитика ===

    public Optional<Double> findMaxWeightForExercise(Long exerciseId) {
        return setRepository.findMaxWeightByExercise(exerciseId);
    }

    public List<Set> getRecentSetsByUserAndExercise(Long userId, Long exerciseId, int limit) {
        return setRepository.findRecentSetsByUserAndExercise(
                userId, exerciseId,
                org.springframework.data.domain.PageRequest.of(0, limit)
        );
    }

    // === Вспомогательное ===

    public boolean existsByWorkoutExerciseAndSetNumber(WorkoutExercise we, int setNumber) {
        return setRepository.existsByWorkoutExercisesAndSetNumber(we, setNumber);
    }
}
