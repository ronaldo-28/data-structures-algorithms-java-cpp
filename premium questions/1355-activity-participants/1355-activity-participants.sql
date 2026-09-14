# Write your MySQL query statement below
WITH cte AS (
    SELECT
        activity,
        COUNT(*) AS count
    FROM Friends
    GROUP BY activity
),
cte2 AS (
    SELECT
        DISTINCT f.activity,
        RANK() OVER(ORDER BY c.count DESC) AS max_rn,
        RANK() OVER(ORDER BY c.count) AS min_rn
    FROM Friends f
    JOIN cte c
        ON c.activity = f.activity
)

SELECT activity
FROM cte2
WHERE max_rn <> 1
  AND min_rn <> 1;