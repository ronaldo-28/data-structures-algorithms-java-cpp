# Write your MySQL query statement below
SELECT user_id AS seller_id, 
(CASE WHEN i.item_brand = u.favorite_brand THEN "yes" ELSE "no" END) AS 2nd_item_fav_brand
FROM Users u LEFT JOIN 
    (SELECT seller_id, item_id
    FROM orders o1
    WHERE 1 = (SELECT COUNT(order_id) FROM orders o2 WHERE o1.seller_id = o2.seller_id AND         o1.order_date>o2.order_date)) t1
ON u.user_id = t1.seller_id
LEFT JOIN Items i
ON t1.item_id = i.item_id
ORDER BY u.user_id;