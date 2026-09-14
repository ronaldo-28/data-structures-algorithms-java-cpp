# Write your MySQL query statement below
SELECT
    CEIL(
        DAYOFMONTH(purchase_date) / 7
    ) AS week_of_month,
    purchase_date,
    SUM(amount_spend) AS total_amount
FROM Purchases
WHERE DAYOFWEEK(purchase_date) = 6      -- friday
GROUP BY 1, 2
ORDER BY 1