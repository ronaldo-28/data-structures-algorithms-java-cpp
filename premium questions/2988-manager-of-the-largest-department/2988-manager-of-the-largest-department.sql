# Write your MySQL query statement below
WITH ranking AS (
    SELECT
        dep_id,
        COUNT(*) AS c,
        RANK() OVER(ORDER BY COUNT(*) DESC) AS rn
    FROM Employees
    GROUP BY dep_id
)


SELECT
    e.emp_name AS manager_name,
    r.dep_id
FROM Employees e
JOIN ranking r
    ON r.dep_id = e.dep_id
WHERE e.position = 'Manager' AND r.rn = 1
ORDER BY r.dep_id