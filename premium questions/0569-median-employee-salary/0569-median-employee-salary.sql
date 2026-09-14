# Write your MySQL query statement below
WITH cte_ord_sal AS (
    SELECT *, 
    ROW_NUMBER() OVER(PARTITION BY company ORDER BY salary) as rn, 
    COUNT(*) OVER(PARTITION BY company) rc
    FROM Employee
)
SELECT 
    id, 
    company, 
    salary 
FROM 
    cte_ord_sal 
WHERE 
    (rc % 2 = 0 AND (rn = rc DIV 2 OR rn = (rc DIV 2) + 1)) 
    OR (rc % 2 = 1 AND (rn = (rc DIV 2) + 1));