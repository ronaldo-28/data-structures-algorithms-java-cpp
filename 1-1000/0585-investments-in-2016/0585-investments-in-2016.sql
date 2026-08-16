# Write your MySQL query statement below
#WITH repeatTIV2015 AS (SELECT COUNT(*), tiv_2015 FROM Insurance GROUP BY tiv_2015 HAVING COUNT(*) > 1). 
#uniqueCities AS

#SELECT * FROM Insurance GROUP BY lat, lon HAVING COUNT(*) = 1

WITH extended AS (SELECT *, COUNT(*) OVER (PARTITION BY tiv_2015) as Count2015Tiv, COUNT(*) OVER (PARTITION BY lat, lon) AS countCities
FROM Insurance)
SELECT ROUND(SUM(tiv_2016),2) AS tiv_2016 FROM  extended WHERE Count2015Tiv > 1 AND countCities = 1