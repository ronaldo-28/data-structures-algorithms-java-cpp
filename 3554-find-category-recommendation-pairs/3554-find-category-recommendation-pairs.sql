# Write your MySQL query statement below
WITH user_categories AS (
    SELECT DISTINCT pp.user_id, pi.category
    FROM ProductPurchases pp
    JOIN ProductInfo pi ON pp.product_id = pi.product_id
)
SELECT
    a.category AS category1,
    b.category AS category2,
    COUNT(*)   AS customer_count
FROM user_categories a
JOIN user_categories b
  ON a.user_id = b.user_id
 AND a.category < b.category
GROUP BY a.category, b.category
HAVING COUNT(*) >= 3
ORDER BY customer_count DESC, category1 ASC, category2 ASC;