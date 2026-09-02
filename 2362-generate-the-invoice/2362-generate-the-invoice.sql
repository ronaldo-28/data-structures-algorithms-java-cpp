# Write your MySQL query statement below
WITH ranking AS (
    SELECT
        pu.invoice_id,
        SUM(pu.quantity * pr.price) AS total,
        RANK() OVER(
            ORDER BY SUM(pu.quantity * pr.price) DESC,
                     pu.invoice_id
        ) AS rn
    FROM Purchases pu
    LEFT JOIN Products pr
        ON pu.product_id = pr.product_id
    GROUP BY pu.invoice_id
)

SELECT
    pu.product_id,
    pu.quantity,
    (pu.quantity * pr.price) AS price
FROM ranking r
JOIN Purchases pu ON r.invoice_id = pu.invoice_id
JOIN Products pr ON pr.product_id = pu.product_id
WHERE r.rn = 1