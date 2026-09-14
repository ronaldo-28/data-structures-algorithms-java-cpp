# Write your MySQL query statement below
SELECT
    emp_id,
    firstname,
    lastname,
    salary,
    department_id
FROM (
    SELECT *,
        ROW_NUMBER() OVER(PARTITION BY emp_id ORDER BY salary DESC) AS rn
    FROM Salary
    ) t
WHERE rn = 1
ORDER BY 1
