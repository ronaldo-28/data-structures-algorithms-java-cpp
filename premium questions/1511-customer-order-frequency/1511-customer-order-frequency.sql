# Write your MySQL query statement below
WITH cte AS (
    SELECT
        o.customer_id,
        o.product_id,
        SUM(CASE
                WHEN YEAR(o.order_date) = '2020' 
                AND MONTH(o.order_date) = 6 
                THEN o.quantity * p.price END
        ) AS june_spent,
        SUM(CASE
                WHEN YEAR(o.order_date) = '2020' 
                AND MONTH(o.order_date) = 7 
                THEN o.quantity * p.price END
        ) AS july_spent
    FROM Orders o
    JOIN Product p
        ON p.product_id = o.product_id
    GROUP BY 1, 2
)

SELECT
    c.customer_id,
    c.name
FROM Customers c
JOIN cte 
    ON c.customer_id = cte.customer_id
GROUP BY 1, 2
HAVING 
    SUM(cte.june_spent) >= 100 
        AND 
    SUM(cte.july_spent) >= 100 ;