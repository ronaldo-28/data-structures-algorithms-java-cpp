# Write your MySQL query statement below
WITH cte AS (
    SELECT
        *, 
        RANK() OVER (PARTITION BY project_id ORDER BY experience_years DESC) AS rnk
    FROM
        Project
    INNER JOIN Employee USING(employee_id)
)

SELECT
    project_id,
    employee_id
FROM cte
WHERE rnk = 1