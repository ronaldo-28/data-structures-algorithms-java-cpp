# Write your MySQL query statement below
SELECT f.flight_id,
       LEAST(COUNT(passenger_id), capacity) AS booked_cnt,
       GREATEST(0, COUNT(passenger_id) - capacity) AS waitlist_cnt
FROM Flights f LEFT JOIN Passengers p ON f.flight_id = p.flight_id
GROUP BY flight_id
ORDER BY flight_id