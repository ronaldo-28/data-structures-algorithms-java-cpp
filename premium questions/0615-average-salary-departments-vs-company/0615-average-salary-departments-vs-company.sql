# Write your MySQL query statement below
WITH 
    ByDepartments AS (
        SELECT
            DATE_FORMAT(pay_date,'%Y-%m') AS pay_month,
            department_id,
            AVG(amount) AS avg_salary
        FROM
            Salary
        INNER JOIN Employee USING (employee_id)
        GROUP BY 
            pay_month,
            department_id
    ),
    ByCompany AS (
        SELECT
            DATE_FORMAT(pay_date,'%Y-%m') AS pay_month,
            AVG(amount) AS avg_salary
        FROM
            Salary
        GROUP BY
            pay_month
    )

SELECT 
    pay_month,
    department_id,
    CASE
        WHEN ByDepartments.avg_salary > ByCompany.avg_salary THEN 'higher'
        WHEN ByDepartments.avg_salary < ByCompany.avg_salary THEN 'lower'
        ELSE 'same'
    END AS comparison
FROM
    ByDepartments
INNER JOIN ByCompany USING (pay_month)