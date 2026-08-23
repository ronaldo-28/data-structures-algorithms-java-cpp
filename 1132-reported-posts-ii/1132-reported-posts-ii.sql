# Write your MySQL query statement below
WITH daily AS (
    SELECT
        action_date,
        COUNT(DISTINCT r.post_id) * 100.0
        / COUNT(DISTINCT a.post_id) AS daily_percent
    FROM Actions a
    LEFT JOIN Removals r
        ON a.post_id = r.post_id
    WHERE a.action = 'report' AND a.extra = 'spam'
    GROUP BY action_date
)

SELECT
    ROUND(AVG(daily_percent), 2) AS average_daily_percent
FROM daily;