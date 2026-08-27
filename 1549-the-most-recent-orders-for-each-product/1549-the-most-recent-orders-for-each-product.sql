# Write your MySQL query statement below
WITH cte AS (
    SELECT
        *,
        RANK() OVER(PARTITION BY product_id 
               ORDER BY order_date DESC) AS rn
    FROM Orders
)
SELECT
    p.product_name,
    p.product_id,
    c.order_id,
    c.order_date
FROM Products p
JOIN cte c
    ON p.product_id = c.product_id
WHERE c.rn = 1
ORDER BY 1, 2, 3