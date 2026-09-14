# Write your MySQL query statement below
WITH RECURSIVE 
    Monthes AS (
        SELECT 
            1 AS mth
        UNION ALL
        SELECT 
            Monthes.mth + 1 AS mth
        FROM
            Monthes
        WHERE 
            Monthes.mth < 12
    ),
    AvailableDriversIn2020ByMonthes AS (
        SELECT
            mth,
            COUNT(driver_id) AS total_available_drivers
        FROM 
            Monthes
        LEFT JOIN Drivers ON Drivers.join_date <= LAST_DAY(CONCAT('2020-', Monthes.mth, '-01'))
        GROUP BY mth
    ),
    RidesIn2020ByMonthes AS (
        SELECT 
            MONTH(Rides.requested_at) AS mth,
            COUNT(DISTINCT driver_id) AS total_working_drivers
        FROM
            Rides
        INNER JOIN AcceptedRides USING (ride_id)
        WHERE 
            YEAR (Rides.requested_at) = 2020
        GROUP BY MONTH(Rides.requested_at)
    )

SELECT 
    mth AS month,
    IFNULL (
        ROUND (
            total_working_drivers / total_available_drivers * 100,
            2
        ), 0
    ) AS working_percentage
FROM Monthes
LEFT JOIN AvailableDriversIn2020ByMonthes USING (mth)
LEFT JOIN RidesIn2020ByMonthes USING (mth)