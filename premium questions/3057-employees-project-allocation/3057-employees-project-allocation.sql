# Write your MySQL query statement below
WITH team_avg AS (
    SELECT
        e.team,
        ROUND(AVG(p.workload), 2) AS avg_work
    FROM Project p
    JOIN Employees e
        ON p.employee_id = e.employee_id
    GROUP BY 1
)

SELECT
    p.employee_id,
    p.project_id,
    e.name AS employee_name,
    p.workload AS project_workload
FROM Project p
JOIN Employees e
    ON p.employee_id = e.employee_id
JOIN team_avg t
    ON t.team = e.team
WHERE p.workload > t.avg_work
ORDER BY 1, 2
