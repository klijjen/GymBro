package ru.lyudofa.srpringcourse.gymbro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lyudofa.srpringcourse.gymbro.model.Exercise;
import ru.lyudofa.srpringcourse.gymbro.model.WorkoutExercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutId(Long workoutId);

    List<WorkoutExercise> findByIdAndWorkoutId(Long id, Long workoutId);

    boolean existsByWorkoutIdAndExerciseId(Long workoutId, Long exerciseId);

    @Query(value = """
        SELECT e
        FROM Exercise e
        WHERE e.muscleGroup
        ILIKE %:muscleGroup%
        """)
    List<Exercise> findByMuscleGroupContainingIgnoreCase(@Param("muscleGroup") String muscleGroup);

    @Query(value = """
        SELECT e
        FROM Exercise e
        WHERE e.createdBy.id = :userId
        """)
    List<Exercise> findByCreatorId(@Param("userId") Long userId);
}
