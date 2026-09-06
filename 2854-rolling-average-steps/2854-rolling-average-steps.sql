# Write your MySQL query statement below
WITH flagged AS (

    SELECT *,

        IF(
            DATEDIFF(
                LEAD(steps_date) OVER(
                    PARTITION BY user_id 
                    ORDER BY steps_date
                ),
                steps_date
            ) = 1,
            1, -- YES Next consecutive day exists
            0  -- NO Next consecutive day exists
        ) AS flag

    FROM Steps
),

checker AS (

    SELECT *,
        -- Check if next row also has a consecutive day (1)
        LEAD(flag) OVER(
            PARTITION BY user_id 
            ORDER BY steps_date
        ) AS next_flag
    FROM flagged
),

last_date AS (

    SELECT *,
        -- 2 rows ahead = last date of the 3-day period
        LEAD(steps_date, 2) OVER(
            PARTITION BY user_id 
            ORDER BY steps_date
        ) AS last,

        -- Current row + next 2 rows = 3-day average
        AVG(steps_count) OVER(
            PARTITION BY user_id
            ORDER BY steps_date
            ROWS BETWEEN CURRENT ROW AND 2 FOLLOWING
        ) AS rolling_average

    FROM checker
)

SELECT
    user_id,
    last AS steps_date,
    ROUND(rolling_average, 2) AS rolling_average

FROM last_date

-- Two consecutive flags = 3 consecutive dates
WHERE flag = 1 AND next_flag = 1

ORDER BY user_id, steps_date
