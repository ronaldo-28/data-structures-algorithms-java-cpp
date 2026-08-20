# Write your MySQL query statement below
SELECT 
    id,
    month,
    SUM(salary) OVER (PARTITION BY id ORDER BY month RANGE BETWEEN 2 PRECEDING AND CURRENT ROW) AS Salary
FROM Employee
WHERE (id, month) NOT IN (SELECT id, MAX(month) AS month FROM Employee GROUP BY id)
ORDER BY id, month DESC