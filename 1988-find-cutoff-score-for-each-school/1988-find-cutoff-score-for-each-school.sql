# Write your MySQL query statement below
WITH ranking AS (
    SELECT
        s.school_id,
        s.capacity,
        e.student_count,
        e.score,
        RANK() OVER(
            PARTITION BY school_id
            ORDER BY student_count DESC, score
        ) AS rn
    FROM Schools s
    JOIN Exam e
        ON s.capacity >= e.student_count
)

SELECT
    school_id,
    score
FROM ranking
WHERE rn = 1

        UNION

SELECT
    school_id,
    -1 AS score
FROM Schools
WHERE school_id NOT IN (
    SELECT school_id
    FROM ranking
);