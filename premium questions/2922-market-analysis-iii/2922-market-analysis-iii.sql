# Write your MySQL query statement below
WITH CTE AS (
    SELECT
        seller_id,
        COUNT(DISTINCT item_id) AS num_items
    FROM
        Orders
    INNER JOIN Items USING(item_id)
    INNER JOIN Users USING(seller_id)
    WHERE item_brand != favorite_brand
    GROUP BY seller_id
)

SELECT
    *
FROM 
    CTE 
WHERE num_items = (
    SELECT 
        MAX(num_items)
    FROM 
        CTE
)