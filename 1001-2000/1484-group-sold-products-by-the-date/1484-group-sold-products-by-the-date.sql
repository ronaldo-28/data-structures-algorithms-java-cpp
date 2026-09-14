SELECT sell_date  ,COUNT(Distinct product) AS num_sold , GROUP_CONCAT( DISTINCT product order by product ASC separator ',' ) AS Products
FROM Activities
GROUP BY sell_date
ORDER BY sell_date