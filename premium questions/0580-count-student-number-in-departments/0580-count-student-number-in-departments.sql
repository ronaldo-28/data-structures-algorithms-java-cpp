# Write your MySQL query statement below
SELECT
    d.dept_name,
    COALESCE(s.count, 0) AS student_number
FROM Department d
LEFT JOIN (
    SELECT
        dept_id,
        COUNT(*) AS count
    FROM Student
    GROUP BY dept_id
) AS s
ON s.dept_id = d.dept_id
ORDER BY 2 DESC, 1;