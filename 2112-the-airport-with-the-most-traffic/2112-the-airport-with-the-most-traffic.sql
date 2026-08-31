# Write your MySQL query statement below
WITH all_airports AS (
    SELECT
        departure_airport AS airport_id,
        flights_count AS cnt
    FROM Flights

    UNION ALL

    SELECT
        arrival_airport,
        flights_count
    FROM Flights
),

total AS (
    SELECT
        airport_id,
        SUM(cnt) AS traffic
    FROM all_airports
    GROUP BY airport_id
),

ranking AS (
    SELECT
        airport_id,
        RANK() OVER (ORDER BY traffic DESC) AS rn
    FROM total
)

SELECT airport_id
FROM ranking
WHERE rn = 1;