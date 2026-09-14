# Write your MySQL query statement below
SELECT 
    gender,
    day,
    sum(score_points) OVER (PARTITION BY gender ORDER BY day) AS total
FROM 
    Scores