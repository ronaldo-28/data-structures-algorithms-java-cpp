# Write your MySQL query statement below
WITH week_membership_combinations AS (
SELECT *
FROM (
    SELECT 1 AS week_of_month UNION ALL
    SELECT 2 UNION ALL
    SELECT 3 UNION ALL
    SELECT 4
) AS weeks
CROSS JOIN (
    SELECT 'Premium' AS membership UNION ALL
    SELECT 'VIP'
) AS types
),

amount_per_membership AS (
SELECT SUM(amount_spend) AS total_amount_base,
       u.membership,
       FLOOR((DAYOFMONTH(p.purchase_date) - 1) / 7) + 1 AS week_of_month
FROM Purchases AS p
JOIN Users AS u USING(user_id)
WHERE DAYOFWEEK(p.purchase_date) = 6
GROUP BY week_of_month, membership)

SELECT wc.week_of_month, 
       wc.membership,
       IFNULL(am.total_amount_base, 0) AS total_amount
FROM week_membership_combinations AS wc 
LEFT JOIN  amount_per_membership AS am
ON am.week_of_month = wc.week_of_month AND am.membership = wc.membership
ORDER BY week_of_month, membership;