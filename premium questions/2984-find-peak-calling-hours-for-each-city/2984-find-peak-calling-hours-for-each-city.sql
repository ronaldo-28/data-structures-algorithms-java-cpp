# Write your MySQL query statement below
WITH extended_calls AS (
    SELECT
        *,
        HOUR(call_time) AS call_hour
    FROM Calls
), cte_calls AS (
    SELECT 
        city,
        call_hour,
        COUNT(*) AS number_of_calls
    FROM extended_calls
    GROUP BY city, call_hour
), cte_calls_ranked AS (
    SELECT
        *,
        RANK () OVER (PARTITION BY city ORDER BY number_of_calls DESC) AS peak_calling_rank
    FROM cte_calls
)

SELECT
    city,
    call_hour AS peak_calling_hour,
    number_of_calls
FROM cte_calls_ranked
WHERE peak_calling_rank = 1
ORDER BY peak_calling_hour DESC, city DESC