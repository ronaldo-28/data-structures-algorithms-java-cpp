# Write your MySQL query statement below
WITH op AS (
    SELECT
        ag.age_bucket,
        SUM(ac.time_spent) AS open
    FROM Activities ac
    JOIN Age ag
        ON ac.user_id = ag.user_id
    WHERE ac.activity_type = 'open'
    GROUP BY ag.age_bucket
),

tot AS (
    SELECT
        ag.age_bucket,
        SUM(ac.time_spent) AS total
    FROM Activities ac
    JOIN Age ag
        ON ac.user_id = ag.user_id
    GROUP BY ag.age_bucket
)

SELECT
    t.age_bucket,
    ROUND(100 - (COALESCE(o.open, 0) * 100 / t.total), 2) AS send_perc,
    ROUND(COALESCE(o.open, 0) * 100 / t.total, 2) AS open_perc
FROM tot t
LEFT JOIN op o
    ON t.age_bucket = o.age_bucket
ORDER BY t.age_bucket;