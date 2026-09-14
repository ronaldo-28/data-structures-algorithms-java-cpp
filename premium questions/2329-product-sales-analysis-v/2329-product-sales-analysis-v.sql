# Write your MySQL query statement below
SELECT
    s.user_id,
    SUM(s.quantity * p.price) AS spending
FROM Sales s
JOIN Product p
    ON s.product_id = p.product_id
GROUP BY 1
ORDER BY 2 DESC, 1