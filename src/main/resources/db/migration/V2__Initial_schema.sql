CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_username ON users(username);

CREATE TABLE exercises (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           description TEXT,
                           muscle_group VARCHAR(50),
                           equipment VARCHAR(50),
                           created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                           created_by INT REFERENCES users(id) ON DELETE SET NULL
);

CREATE INDEX idx_exercises_muscle_group ON exercises(muscle_group);

CREATE TABLE workouts (
                          id SERIAL PRIMARY KEY,
                          user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          name VARCHAR(100),
                          notes TEXT,
                          start_time TIMESTAMP WITH TIME ZONE,
                          end_time TIMESTAMP WITH TIME ZONE,
                          created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_workouts_user_id ON workouts(user_id);
CREATE INDEX idx_workouts_date ON workouts(start_time);

CREATE TABLE workout_exercises (
                                   id SERIAL PRIMARY KEY,
                                   workout_id INT NOT NULL REFERENCES workouts(id) ON DELETE CASCADE,
                                   exercise_id INT NOT NULL REFERENCES exercises(id) ON DELETE RESTRICT,
                                   order_index INT NOT NULL,  -- Порядок выполнения в тренировке
                                   notes TEXT,
                                   created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_workout_exercises_workout ON workout_exercises(workout_id);

CREATE TABLE sets (
                      id SERIAL PRIMARY KEY,
                      workout_exercise_id INT NOT NULL REFERENCES workout_exercises(id) ON DELETE CASCADE,
                      set_number INT NOT NULL,       -- Номер подхода (1, 2, 3...)
                      reps INT NOT NULL,            -- Количество повторений
                      weight_kg DECIMAL(5,2),       -- Вес в кг (NULL для упражнений без веса)
                      rpe DECIMAL(3,1),            -- Субъективная сложность (Rate of Perceived Exertion)
                      rest_interval INTERVAL,       -- Время отдыха после подхода
                      completed BOOLEAN DEFAULT TRUE,
                      notes TEXT,
                      created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_sets_workout_exercise ON sets(workout_exercise_id);

CREATE TABLE personal_records (
                                  id SERIAL PRIMARY KEY,
                                  user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                  exercise_id INT NOT NULL REFERENCES exercises(id) ON DELETE CASCADE,
                                  value DECIMAL(8,2) NOT NULL,  -- Значение рекорда (вес или время)
                                  is_weight BOOLEAN NOT NULL,    -- True для веса, False для времени
                                  date_achieved DATE NOT NULL,
                                  notes TEXT,
                                  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                  UNIQUE(user_id, exercise_id, is_weight)  -- Один рекорд данного типа на упражнение
);

CREATE INDEX idx_personal_records_user ON personal_records(user_id);

CREATE VIEW user_progress AS
SELECT
    u.id AS user_id,
    e.id AS exercise_id,
    e.name AS exercise_name,
    MAX(s.weight_kg) AS max_weight,
    MAX(s.reps) AS max_reps,
    COUNT(DISTINCT w.id) AS workouts_count
FROM users u
         JOIN workouts w ON w.user_id = u.id
         JOIN workout_exercises we ON we.workout_id = w.id
         JOIN exercises e ON e.id = we.exercise_id
         JOIN sets s ON s.workout_exercise_id = we.id
GROUP BY u.id, e.id, e.name;


CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER set_updated_at_users
    BEFORE UPDATE ON users
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER set_updated_at_exercises
    BEFORE UPDATE ON exercises
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER set_updated_at_workouts
    BEFORE UPDATE ON workouts
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER set_updated_at_workout_exercises
    BEFORE UPDATE ON workout_exercises
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER set_updated_at_sets
    BEFORE UPDATE ON sets
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER set_updated_at_personal_records
    BEFORE UPDATE ON personal_records
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();

