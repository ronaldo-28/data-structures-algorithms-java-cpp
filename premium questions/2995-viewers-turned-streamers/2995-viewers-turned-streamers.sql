# Write your MySQL query statement below

WITH cte AS (
    SELECT
        user_id,
        session_type,
        RANK() OVER(PARTITION BY user_id ORDER BY session_start) AS rn
    FROM sessions)

SELECT 
    user_id,
    SUM(session_type = 'Streamer') as sessions_count
FROM cte
WHERE user_id IN (
    SELECT user_id 
    FROM cte
        WHERE rn = 1 AND session_type = 'Viewer'
)

GROUP BY 1
HAVING SUM(session_type = 'Streamer') > 0
ORDER BY 2 DESC, 1 DESC