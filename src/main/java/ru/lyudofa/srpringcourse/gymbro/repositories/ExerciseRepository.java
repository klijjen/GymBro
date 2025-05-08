package ru.lyudofa.srpringcourse.gymbro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lyudofa.srpringcourse.gymbro.model.Exercise;
import ru.lyudofa.srpringcourse.gymbro.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByNameIgnoreCase(String name);

    List<Exercise> findByMuscleGroupIgnoreCase(String muscleGroup);

    List<Exercise> findByCreatedBy(User createdBy);

    @Query(value = """
        SELECT e
        FROM Exercise e
        WHERE LOWER(e.name)
        LIKE LOWER(concat('%', :query, '%'))
        """)
    List<Exercise> searchByName(@Param("query") String searchQuery);

    @Query("""
        SELECT DISTINCT e.muscleGroup 
        FROM Exercise e 
        WHERE e.muscleGroup IS NOT NULL
        """)
    List<String> findAllMuscleGroups();

    @Query(value = """
            SELECT e, COUNT(we) as usageCount
            FROM Exercise e LEFT JOIN WorkoutExercise we ON e.id = we.exercise.id
            GROUP BY e.id
            ORDER BY usageCount DESC
            """)
    List<Object[]> findMostPopularExercises();
}
