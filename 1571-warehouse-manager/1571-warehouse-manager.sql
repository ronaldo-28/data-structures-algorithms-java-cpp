# Write your MySQL query statement below
SELECT
    w.name AS warehouse_name,
    SUM(w.units * pv.product_volume) AS volume
FROM Warehouse w
LEFT JOIN (
    SELECT
        product_id,
        width * Length * Height AS product_volume
    FROM Products
) pv
ON w.product_id = pv.product_id
GROUP BY w.name;