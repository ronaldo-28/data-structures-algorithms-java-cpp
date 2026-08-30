# Write your MySQL query statement below
WITH next_msg AS (
    SELECT *,
           LEAD(time_stamp) OVER (
               PARTITION BY user_id
               ORDER BY time_stamp
           ) AS next
    FROM Confirmations
)

SELECT DISTINCT user_id
FROM next_msg
WHERE TIMESTAMPDIFF(SECOND, time_stamp, next) <= 24 * 60 * 60
GROUP BY user_id;