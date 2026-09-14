# Write your MySQL query statement below
WITH CTE AS (
    SELECT
        salesperson_id,
        SUM(price) AS total
    FROM
        Sales
    INNER JOIN Customer USING (customer_id)
    GROUP BY salesperson_id
)

SELECT
    salesperson_id,
    name,
    IFNULL(total, 0) AS total
FROM
    Salesperson
LEFT JOIN CTE USING (salesperson_id)