# Write your MySQL query statement below
WITH first_logins AS (
    SELECT
        user_id,
        MIN(activity_date) AS login_date
    FROM Traffic
    WHERE activity = 'login'
    GROUP BY user_id
)
SELECT
    login_date,
    COUNT(*) AS user_count
FROM first_logins
GROUP BY login_date
HAVING DATEDIFF('2019-06-30' ,login_date)
        BETWEEN 0 AND 90