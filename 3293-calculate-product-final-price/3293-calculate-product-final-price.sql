# Write your MySQL query statement below
SELECT 
    product_id,
    price * (100 - IFNULL(discount, 0))/ 100 final_price,
    Products.category AS category
FROM Products
LEFT JOIN Discounts USING(category)