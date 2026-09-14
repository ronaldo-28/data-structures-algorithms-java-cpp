# Write your MySQL query statement below
WITH outflow AS (
    SELECT
        paid_by,
        - SUM(amount) AS credit
    FROM Transactions
    GROUP BY 1
),

inflow AS (
    SELECT
        paid_to,
        SUM(amount) AS credit
    FROM Transactions
    GROUP BY 1
)

SELECT
    u.user_id,
    u.user_name,

    COALESCE(
        (u.credit + COALESCE(o.credit, 0)
         + COALESCE(i.credit, 0)
        ), u.credit
    ) AS credit,

    CASE
        WHEN (u.credit + COALESCE(o.credit, 0)
              + COALESCE(i.credit, 0)) < 0
        THEN 'Yes' ELSE 'No'
    END AS credit_limit_breached

FROM Users u
LEFT JOIN outflow o ON u.user_id = o.paid_by
LEFT JOIN inflow i ON u.user_id = i.paid_to ;