# Write your MySQL query statement below
WITH CoPurchases AS (
    -- 1. Find pairs of products bought by the exact same user
    SELECT 
        pp1.product_id AS product1_id,
        pp2.product_id AS product2_id,
        COUNT(pp1.user_id) AS customer_count
    FROM ProductPurchases pp1
    JOIN ProductPurchases pp2 
        ON pp1.user_id = pp2.user_id            -- Must be the same customer
        AND pp1.product_id < pp2.product_id     -- Ensure unique pairs (e.g., 101-102, no duplicates)
    GROUP BY 
        pp1.product_id, 
        pp2.product_id
    HAVING COUNT(pp1.user_id) >= 3              -- Must be bought together by at least 3 users
)
-- 2. Join with ProductInfo to grab the categories for both products
SELECT 
    cp.product1_id,
    cp.product2_id,
    info1.category AS product1_category,
    info2.category AS product2_category,
    cp.customer_count
FROM CoPurchases cp
JOIN ProductInfo info1 ON cp.product1_id = info1.product_id
JOIN ProductInfo info2 ON cp.product2_id = info2.product_id
ORDER BY 
    cp.customer_count DESC, 
    cp.product1_id ASC, 
    cp.product2_id ASC;