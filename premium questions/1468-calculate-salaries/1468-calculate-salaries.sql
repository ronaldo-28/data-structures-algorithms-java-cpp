# Write your MySQL query statement below
WITH benchmark AS (
    SELECT
        company_id,
        MAX(salary) AS highest
    FROM Salaries
    GROUP BY company_id
)

SELECT
    s.company_id,
    s.employee_id,
    s.employee_name,
    CASE 
        WHEN b.highest > 10000 
            THEN ROUND(s.salary - (0.49 * s.salary))
        WHEN b.highest > 999 
            THEN ROUND(s.salary - (0.24 * s.salary))
        ELSE ROUND(s.salary)
    END AS salary
FROM Salaries s
JOIN benchmark b
    ON s.company_id = b.company_id