# Write your MySQL query statement below
WITH cte AS (
    SELECT
        *,
        ROW_NUMBER() OVER(PARTITION BY customer_id 
                    ORDER BY order_date DESC) AS rn
    FROM Orders
)
SELECT
    c.name AS customer_name,
    c.customer_id,
    c1.order_id,
    c1.order_date
FROM cte c1
JOIN Customers c
    ON c1.customer_id = c.customer_id
WHERE c1.rn <= 3
ORDER BY 1, 2, 4 DESC;