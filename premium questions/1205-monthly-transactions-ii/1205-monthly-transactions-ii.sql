# Write your MySQL query statement below
WITH cte1 AS (
    SELECT
        DATE_FORMAT(trans_date, '%Y-%m') AS month,
        country,
        SUM(IF(state = 'approved', 1, 0)) AS approved_count,
        SUM(IF(state = 'approved', amount, 0)) AS approved_amount,
        0 AS chargeback_count,
        0 AS chargeback_amount
    FROM Transactions
    GROUP BY 1, 2
),

cte2 AS (
    SELECT
        DATE_FORMAT(cb.trans_date, '%Y-%m') AS month,
        t.country,
        0 AS approved_count,
        0 AS approved_amount,
        COUNT(*) AS chargeback_count,
        SUM(t.amount) AS chargeback_amount
    FROM Chargebacks cb
    LEFT JOIN Transactions t
        ON cb.trans_id = t.id
    GROUP BY 1, 2

),

cte3 AS (
SELECT
    month, country, approved_count, approved_amount,
    chargeback_count, chargeback_amount
FROM cte1

        UNION ALL

SELECT
    month, country, approved_count, approved_amount,
    chargeback_count, chargeback_amount
FROM cte2
)

SELECT
    c3.month,
    c3.country,
    IFNULL(SUM(c3.approved_count), 0) AS approved_count,
    IFNULL(SUM(c3.approved_amount), 0) AS approved_amount,
    SUM(c3.chargeback_count) AS chargeback_count,
    SUM(c3.chargeback_amount) AS chargeback_amount
FROM cte3 c3
WHERE (approved_count + approved_amount +
       chargeback_count + chargeback_amount) > 0
GROUP BY 1, 2
