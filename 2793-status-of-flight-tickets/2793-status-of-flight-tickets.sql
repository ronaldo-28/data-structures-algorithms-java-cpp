# Write your MySQL query statement below
WITH ranking AS (
    SELECT
        *,
        ROW_NUMBER() OVER(
                PARTITION BY flight_id 
                ORDER BY booking_time
        ) AS rn
    FROM Passengers
)

SELECT
    passenger_id,
    Status
FROM (
    SELECT
        r.passenger_id,
        'Confirmed' AS Status
    FROM ranking r
    JOIN Flights f
        ON f.flight_id = r.flight_id
    WHERE r.rn <= f.capacity

        UNION ALL

    SELECT
        r.passenger_id,
        'Waitlist' AS Status
    FROM ranking r
    JOIN Flights f
        ON f.flight_id = r.flight_id
    WHERE r.rn > f.capacity
) AS c
ORDER BY passenger_id