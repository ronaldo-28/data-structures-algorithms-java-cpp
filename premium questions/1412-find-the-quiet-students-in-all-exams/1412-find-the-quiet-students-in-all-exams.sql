# Write your MySQL query statement below
WITH ranking AS (
    SELECT *,
        RANK() OVER(PARTITION BY exam_id 
                ORDER BY score) AS lowest_rn,
        RANK() OVER(PARTITION BY exam_id 
                ORDER BY score DESC) AS highest_rn
    FROM Exam
),

student_id AS (
    SELECT
        DISTINCT student_id
    FROM ranking
    WHERE student_id NOT IN (
        SELECT
            student_id 
        FROM ranking
        WHERE lowest_rn = 1 OR highest_rn = 1
    )
)

SELECT
    s.student_id,
    s1.student_name
FROM student_id s
JOIN Student s1
    ON s.student_id = s1.student_id


