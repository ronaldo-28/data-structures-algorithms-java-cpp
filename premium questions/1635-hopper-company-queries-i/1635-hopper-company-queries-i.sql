# Write your MySQL query statement below
WITH RECURSIVE 
    Monthes AS (
        SELECT
            1 AS mth
        UNION ALL
        SELECT 
            Monthes.mth + 1
        FROM 
            Monthes
        WHERE
            Monthes.mth < 12
    ),
    ActiveDriversByMonthesIn2020 AS (
        SELECT 
            mth,
            COUNT(DISTINCT driver_id) AS active_drivers
        FROM
            Monthes
        LEFT JOIN Drivers ON 
            Drivers.join_date <= LAST_DAY(CONCAT(2020, '-', mth, '-01'))
        GROUP BY
            mth
    ),
    RidesIn2020 AS (
        SELECT
            *,
            MONTH(requested_at) AS mth
        FROM
            AcceptedRides
        INNER JOIN Rides USING(ride_id)
        WHERE YEAR(requested_at) = 2020
    ),
    RidesByMonthesIn2020 AS (
        SELECT
            mth,
            COUNT(ride_id) AS accepted_rides
        FROM
            Monthes
        LEFT JOIN RidesIn2020 USING (mth)
        GROUP BY
            mth
    )
    
SELECT
    mth AS month,
    active_drivers,
    accepted_rides
FROM
    Monthes
INNER JOIN RidesByMonthesIn2020 USING (mth)
INNER JOIN ActiveDriversByMonthesIn2020 USING (mth)
ORDER BY month ASC