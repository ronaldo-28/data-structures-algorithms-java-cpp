# Write your MySQL query statement below

with cte as (
SELECT id,drink,row_number() OVER () as new_order
FROM CoffeeShop),

cte2 as (
SELECT *,sum(drink is NOT null) OVER (order by new_order) as order1
FROM cte)

SELECT cte2.id,t3.drink
FROM cte2

LEFT JOIN

(SELECT *
FROM cte2
WHERE drink IS NOT NULL) t3

ON cte2.order1=t3.order1