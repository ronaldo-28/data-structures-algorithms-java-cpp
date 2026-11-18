# Write your MySQL query statement below
SELECT 
    d.driver_id,
    d.driver_name,
    ROUND(AVG(CASE WHEN MONTH(t.trip_date) <= 6 THEN t.distance_km / t.fuel_consumed END), 2) AS first_half_avg,
    ROUND(AVG(CASE WHEN MONTH(t.trip_date) > 6 THEN t.distance_km / t.fuel_consumed END), 2) AS second_half_avg,
    ROUND(
        AVG(CASE WHEN MONTH(t.trip_date) > 6 THEN t.distance_km / t.fuel_consumed END) - 
        AVG(CASE WHEN MONTH(t.trip_date) <= 6 THEN t.distance_km / t.fuel_consumed END), 
        2
    ) AS efficiency_improvement
FROM drivers d
JOIN trips t ON d.driver_id = t.driver_id
GROUP BY d.driver_id, d.driver_name
HAVING first_half_avg IS NOT NULL 
   AND second_half_avg IS NOT NULL 
   AND efficiency_improvement > 0
ORDER BY efficiency_improvement DESC, d.driver_name ASC;