INSERT INTO workouts (user_id, name, notes, start_time, end_time, created_at)
SELECT
    u.id AS user_id,
    CASE
        WHEN u.email LIKE '%example.com' THEN 'Утренняя тренировка'
        WHEN u.email LIKE '%test.org' THEN 'Силовая сессия'
        WHEN u.email LIKE '%mail.ru' THEN 'Кардио нагрузка'
        WHEN u.email LIKE '%edu.misis.ru' THEN 'Студенческая зарядка'
        ELSE 'Стандартная тренировка'
        END AS name,
    CASE
        WHEN u.email = 'user1@example.com' THEN 'Разминка + основные упражнения'
        WHEN u.email = 'NastyaPoop@edu.misis.ru' THEN 'Упражнения по расписанию'
        ELSE NULL
        END AS notes,
    NOW() - (random() * INTERVAL '30 days') AS start_time,
    NOW() - (random() * INTERVAL '29 days') AS end_time,
    NOW() - (random() * INTERVAL '30 days') AS created_at
FROM users u
WHERE u.email IN (
                  'user1@example.com',
                  'user2@example.com',
                  'another@test.org',
                  'nkjkjhkj@mail.ru',
                  'hsidufta@mail.xyz',
                  'NastyaPoop@edu.misis.ru',
                  'useJr1@example.com'
    )
ORDER BY random()
    LIMIT 20;