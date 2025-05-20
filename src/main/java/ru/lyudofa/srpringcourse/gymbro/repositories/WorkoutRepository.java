package ru.lyudofa.srpringcourse.gymbro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lyudofa.srpringcourse.gymbro.model.Workout;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    Optional<Workout> findByNameIgnoreCase(String name);

    List<Workout> findByUserId(Integer userId);

    List<Workout> findByUserIdAndStartTimeBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );

    @Query(value = """
            SELECT w FROM Workout
            w LEFT JOIN FETCH w.exercises
            WHERE w.id = :id
            """)
    Optional<Workout> findByIdWithExercises(@Param("id") Long id);

    @Query(value = """
            SELECT COUNT(w)
            FROM Workout w
            WHERE w.user.id = :userId
            """)
    long countWorkoutsByUser(@Param("userId") Long userId);

    @Query(value = """
        SELECT DATE_TRUNC('week', w.start_time) AS week, COUNT(*) AS workout_count
        FROM workouts w
        WHERE w.user_id = :userId
        GROUP BY week
        ORDER BY week DESC
        """, nativeQuery = true)
    List<Object[]> getWeeklyStats(@Param("userId") Long userId);

}
