# Write your MySQL query statement below
WITH
    CTE AS (
        SELECT
            *,
            SUM(salary) OVER(
                PARTITION BY experience
                ORDER BY salary
            ) AS summed
        FROM
            Candidates
    ),
    SeniorTotal AS (
        SELECT
            employee_id
        FROM CTE
        WHERE experience = 'Senior' AND summed <= 70000
    ),
    RemainBugdet AS (
        SELECT
            70000 - IFNULL(MAX(summed), 0) AS remain_budget
        FROM CTE
        WHERE experience = 'Senior' AND summed <= 70000
    ),
    JuniorTotal AS (
        SELECT
            employee_id
        FROM CTE
        WHERE experience = 'Junior' AND summed <= (
            SELECT remain_budget FROM RemainBugdet
        ) 
    )

SELECT 
    employee_id
FROM SeniorTotal
UNION ALL
SELECT 
    employee_id
FROM JuniorTotal