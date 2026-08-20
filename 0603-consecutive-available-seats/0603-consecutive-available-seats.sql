# Write your MySQL query statement below
WITH cinema_windowed AS (
    SELECT 
        *,
        LAG(free, 1) OVER(ORDER BY seat_id ASC) AS prev_seat,
        LEAD(free, 1) OVER(ORDER BY seat_id ASC) AS next_seat
    FROM cinema
)
SELECT seat_id
FROM cinema_windowed
WHERE free = 1 AND (free = prev_seat OR free = next_seat)
ORDER BY seat_id