# Write your MySQL query statement below
WITH rn_info AS (
SELECT ROW_NUMBER() OVER (PARTITION BY customer_id ORDER BY transaction_date) AS rn, customer_id, transaction_date 
FROM (SELECT DISTINCT customer_id, transaction_date FROM Transactions) temp),

customer_with_base_date AS (
SELECT customer_id, DATE_SUB(transaction_date, INTERVAL rn DAY) AS base_date FROM rn_info),

customer_with_count AS (
SELECT customer_id, COUNT(*) AS c FROM customer_with_base_date GROUP BY customer_id, base_date)

SELECT customer_id FROM customer_with_count WHERE c IN (SELECT MAX(c) FROM customer_with_count)