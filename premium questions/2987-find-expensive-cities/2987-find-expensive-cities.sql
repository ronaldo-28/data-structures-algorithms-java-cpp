# Write your MySQL query statement below
SELECT city 
FROM Listings
GROUP BY city 
HAVING AVG(Price)> (SELECT  avg(price) FROM Listings)
ORDER BY city