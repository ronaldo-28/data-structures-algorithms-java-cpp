# Write your MySQL query statement below
WITH prev AS (
    SELECT
        log_id,
        LAG(log_id, 1) OVER(ORDER BY log_id) AS prev
    FROM Logs
),
flag AS (
    SELECT
        *,
        IF((log_id - prev <> 1), 1, 0) AS flag
    FROM prev
),
streaks AS (
    SELECT
        *,
        SUM(flag) OVER(ORDER BY log_id) AS ranges
    FROM flag
)
SELECT
    MIN(log_id) AS start_id,
    MAX(log_id) AS end_id
FROM streaks
GROUP BY ranges