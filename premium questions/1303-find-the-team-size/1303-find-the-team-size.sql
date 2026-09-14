# Write your MySQL query statement below
WITH team_size AS (
    SELECT
        team_id,
        COUNT(*) AS team_size
    FROM Employee
    GROUP BY team_id
)
SELECT
    e.employee_id,
    t.team_size
FROM Employee e
JOIN team_size t
    ON t.team_id = e.team_id