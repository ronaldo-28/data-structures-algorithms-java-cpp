# Write your MySQL query statement below
SELECT

    ABS(
        MAX(CASE WHEN department = 'Marketing' THEN salary END)
        -
        MAX(CASE WHEN department = 'Engineering' THEN salary END)
    ) AS salary_difference

FROM Salaries