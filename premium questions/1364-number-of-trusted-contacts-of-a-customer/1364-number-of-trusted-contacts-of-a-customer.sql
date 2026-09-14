# Write your MySQL query statement below
WITH c_count AS (
    SELECT
        user_id,
        COUNT(*) AS c_cnt
    FROM Contacts
    GROUP BY user_id
),

trusted_c_count AS (
    SELECT
        user_id,
        COUNT(*) AS t_c_cnt
    FROM Contacts co
    JOIN Customers cu
        ON co.user_id = cu.customer_id
    WHERE co.contact_name IN (
        SELECT
            customer_name
        FROM Customers
    )
    GROUP BY user_id
)

SELECT
    i.invoice_id,
    cu.customer_name,
    i.price,
    COALESCE(cc.c_cnt, 0) AS contacts_cnt,
    COALESCE(tcc.t_c_cnt, 0) AS trusted_contacts_cnt 
FROM Invoices i
JOIN Customers cu ON i.user_id = cu.customer_id
LEFT JOIN c_count cc ON i.user_id = cc.user_id
LEFT JOIN trusted_c_count AS tcc ON i.user_id = tcc.user_id
ORDER BY 1