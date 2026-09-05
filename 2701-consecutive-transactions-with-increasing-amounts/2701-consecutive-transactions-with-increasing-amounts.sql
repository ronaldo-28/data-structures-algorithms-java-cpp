# Write your MySQL query statement below

WITH CTE1 AS(
    SELECT 
        *
        ,LAG(amount) OVER(PARTITION BY customer_id ORDER BY transaction_date) AS prev_amount
        ,LAG(transaction_date) OVER(PARTITION BY customer_id ORDER BY transaction_date) AS pre_transaction_date
    FROM 
        Transactions
    ORDER BY
        customer_id, transaction_date
),

CTE2 AS(
    SELECT 
        *
        ,TO_DAYS(transaction_date) - RANK() OVER(PARTITION BY customer_id ORDER BY transaction_date) AS GRP
    FROM CTE1 
    WHERE amount > prev_amount AND DATEDIFF(transaction_date, pre_transaction_date) = 1
)


SELECT 
    customer_id,
    MIN(pre_transaction_date) AS consecutive_start,
    MAX(transaction_date) AS consecutive_end
FROM 
    CTE2
GROUP BY 
    customer_id, GRP
HAVING COUNT(GRP) >= 2