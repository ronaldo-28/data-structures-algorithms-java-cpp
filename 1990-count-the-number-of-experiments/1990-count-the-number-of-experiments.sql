# Write your MySQL query statement below
WITH platforms AS (
    SELECT 'Android' AS platform
    UNION ALL
    SELECT 'IOS' AS platform
    UNION ALL
    SELECT 'Web' AS platform
),
activities AS (
    SELECT 'Reading' AS activity
    UNION ALL
    SELECT 'Sports' AS activity
    UNION ALL
    SELECT 'Programming' AS activity
)

SELECT
    p.platform,
    a.activity AS experiment_name,
    SUM(CASE
        WHEN ep.experiment_name = a.activity THEN 1 ELSE 0
    END) AS num_experiments
FROM platforms p
JOIN activities a
LEFT JOIN experiments ep ON p.platform = ep.platform AND a.activity = ep.experiment_name
GROUP BY p.platform, a.activity;