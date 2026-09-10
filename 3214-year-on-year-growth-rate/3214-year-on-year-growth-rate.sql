# Write your MySQL query statement below
WITH info AS (SELECT product_id, SUM(spend) AS spend, YEAR(transaction_date) AS year FROM user_transactions GROUP BY product_id, YEAR(transaction_date))


SELECT a.year, a.product_id, a.spend AS curr_year_spend, b.spend AS prev_year_spend, ROUND((a.spend - b.spend) * 100.0 / b.spend, 2) AS yoy_rate 
FROM info a LEFT JOIN info b ON a.product_id = b.product_id AND a.year = b.year + 1 ORDER BY product_id, year