# Write your MySQL query statement below
SELECT 
    e.employee_id
FROM Employees e
LEFT JOIN Employees m1
    ON e.manager_id = m1.employee_id
LEFT JOIN Employees m2
    ON m1.manager_id = m2.employee_id
LEFT JOIN Employees m3
    ON m2.manager_id = m3.employee_id
WHERE
    e.employee_id <> 1
    AND(   m1.employee_id = 1
        OR m2.employee_id = 1
        OR m3.employee_id = 1
    );