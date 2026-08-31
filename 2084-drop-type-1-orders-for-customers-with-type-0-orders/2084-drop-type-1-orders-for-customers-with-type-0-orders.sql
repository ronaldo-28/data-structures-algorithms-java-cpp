# Write your MySQL query statement below
SELECT *
FROM Orders
WHERE order_type = 0

UNION

SELECT *
FROM Orders
WHERE order_type = 1
    AND customer_id NOT IN (
        SELECT customer_id
        FROM Orders
        WHERE order_type = 0
);