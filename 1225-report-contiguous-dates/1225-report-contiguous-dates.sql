# Write your MySQL query statement below
WITH all_dates AS (
    SELECT
        fail_date AS dates,
        'failed' AS event
    FROM Failed
    WHERE fail_date BETWEEN '2019-01-01' AND '2019-12-31'
            UNION 
    SELECT
        success_date,
        'succeeded'
    FROM Succeeded
    WHERE success_date BETWEEN '2019-01-01' AND '2019-12-31'
),

breaks AS (
    SELECT
        dates,
        event,
        CASE 
            WHEN event <> LAG(event) OVER(ORDER BY dates)
            THEN 1 ELSE 0
        END AS flag
    FROM all_dates
    ORDER BY dates
),

clubs AS (
    SELECT
        dates,
        event,
        SUM(flag) OVER(ORDER BY dates) AS gr
    FROM breaks
)

SELECT
    event AS period_state,
    MIN(dates) AS start_date,
    MAX(dates) AS end_date
FROM clubs
GROUP BY gr 

