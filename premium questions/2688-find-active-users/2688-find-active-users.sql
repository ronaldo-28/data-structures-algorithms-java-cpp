# Write your MySQL query statement below
SELECT
    DISTINCT a.user_id
FROM Users a
JOIN Users b
    ON a.user_id = b.user_id 
    AND b.created_at BETWEEN 
        a.created_at AND 
        DATE_ADD(a.created_at, INTERVAL 7 DAY)
GROUP BY
    a.user_id,
    a.created_at
HAVING COUNT(*) >= 2;