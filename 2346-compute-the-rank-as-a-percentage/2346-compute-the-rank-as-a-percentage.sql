# Write your MySQL query statement below
WITH total AS (
    SELECT
        department_id,
        COUNT(*) AS total_students
    FROM Students
    GROUP BY department_id
)

SELECT
    s.student_id,
    s.department_id,
    IFNULL(
        ROUND(
            (RANK() OVER(
            PARTITION BY s.department_id
            ORDER BY s.mark DESC) - 1) * 100 
            / 
            (t.total_students - 1)
        , 2)
    , 0) AS percentage
FROM Students s
JOIN total t
    ON s.department_id = t.department_id