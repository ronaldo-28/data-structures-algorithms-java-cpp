# Write your MySQL query statement below
WITH cte AS (
    SELECT
        x,
        LAG(x, 1) OVER(ORDER BY x) AS prev
    FROM Point
)
SELECT
    ABS(MIN(x-prev)) AS shortest
FROM cte