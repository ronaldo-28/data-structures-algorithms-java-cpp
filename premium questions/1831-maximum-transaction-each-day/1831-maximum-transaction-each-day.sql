# Write your MySQL query statement below
WITH cte AS (
    SELECT
        *,
        RANK() OVER(PARTITION BY DATE(day)
               ORDER BY amount DESC) AS rn
    FROM Transactions
)
SELECT
    transaction_id
FROM cte
WHERE rn = 1
ORDER BY 1