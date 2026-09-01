# Write your MySQL query statement below
WITH passenger_cnt AS (
    SELECT
        passenger_id,
        COUNT(passenger_id) AS cnt
    FROM Rides
    GROUP BY 1
)

SELECT
    DISTINCT r.driver_id,
    COALESCE(p.cnt, 0) AS cnt
FROM Rides r
LEFT JOIN passenger_cnt p
    ON r.driver_id = p.passenger_id
