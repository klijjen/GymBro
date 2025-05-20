package ru.lyudofa.srpringcourse.gymbro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lyudofa.srpringcourse.gymbro.model.Set;
import ru.lyudofa.srpringcourse.gymbro.model.WorkoutExercise;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findByWorkoutExercisesId(Integer workoutExercises_id);

    @Query("SELECT s FROM Set s WHERE s.workoutExercises.id = :exerciseId ORDER BY s.setNumber")
    List<Set> findOrderedSetsByExercise(@Param("exerciseId") Long workoutExerciseId);

    // 2. Методы для аналитики
    @Query("SELECT MAX(s.weightKg) FROM Set s WHERE s.workoutExercises.exercise.id = :exerciseId")
    Optional<Double> findMaxWeightByExercise(@Param("exerciseId") Long exerciseId);

    @Query("""
        SELECT s 
        FROM Set s 
        WHERE s.workoutExercises.workout.user.id = :userId AND s.workoutExercises.exercise.id = :exerciseId 
        ORDER BY s.createdAt DESC
        """)
    List<Set> findRecentSetsByUserAndExercise(@Param("userId") Long userId,
                                              @Param("exerciseId") Long exerciseId,
                                              Pageable pageable);

    @Modifying
    @Query("UPDATE Set s SET s.weightKg = :weight WHERE s.id = :id")
    void updateWeight(@Param("id") Long setId, @Param("weight") Double weight);

    @Modifying
    @Query("DELETE FROM Set s WHERE s.workoutExercises.id = :workoutExerciseId")
    void deleteAllByWorkoutExerciseId(@Param("workoutExerciseId") Long workoutExerciseId);

    boolean existsByWorkoutExercisesAndSetNumber(WorkoutExercise workoutExercise, Integer setNumber);
}
