package ru.lyudofa.srpringcourse.gymbro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lyudofa.srpringcourse.gymbro.model.Exercise;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.repositories.ExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise createExercise(User creator, Exercise exercise) {
        exercise.setCreatedBy(creator);
        return exerciseRepository.save(exercise);
    }

    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Optional<Exercise> findByName(String name) {
        return exerciseRepository.findByNameIgnoreCase(name);
    }

    public List<Exercise> findByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findByMuscleGroupIgnoreCase(muscleGroup);
    }

    public List<Exercise> searchByName(String query) {
        return exerciseRepository.searchByName(query);
    }

    public List<String> getAllMuscleGroups() {
        return exerciseRepository.findAllMuscleGroups();
    }

    public List<Object[]> findMostPopularExercises() {
        return exerciseRepository.findMostPopularExercises();
    }

    public Exercise updateExercise(Long id, Exercise updated) {
        Exercise existing = exerciseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setMuscleGroup(updated.getMuscleGroup());
        existing.setEquipment(updated.getEquipment());

        return exerciseRepository.save(existing);
    }

    public void deleteExercise(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new IllegalArgumentException("Exercise not found");
        }
        exerciseRepository.deleteById(id);
    }
}
