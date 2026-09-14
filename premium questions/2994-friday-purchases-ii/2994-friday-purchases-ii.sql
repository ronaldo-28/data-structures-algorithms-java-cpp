# Write your MySQL query statement below
WITH RECURSIVE fridays AS (

    -- first Friday
    SELECT
        DATE_ADD(
            DATE_FORMAT(MIN(purchase_date), '%Y-%m-01'),
            INTERVAL(
                (4 - WEEKDAY(DATE_FORMAT(MIN(purchase_date), '%Y-%m-01'))) % 7
            ) DAY
        ) AS purchase_date
    FROM Purchases

    UNION ALL

    -- next Friday
    SELECT
        DATE_ADD(purchase_date, INTERVAL 7 DAY)
    FROM fridays
    WHERE DATE_ADD(purchase_date, INTERVAL 7 DAY) < DATE_ADD(
        DATE_FORMAT((SELECT MIN(purchase_date) FROM Purchases), '%Y-%m-01'),
        INTERVAL 1 MONTH
    )
)

SELECT
    ROW_NUMBER() OVER(ORDER BY f.purchase_date) AS week_of_month,
    f.purchase_date,
    COALESCE(SUM(p.amount_spend), 0) AS total_amount
FROM fridays f
LEFT JOIN Purchases p
    ON p.purchase_date = f.purchase_date
GROUP BY f.purchase_date
ORDER BY f.purchase_date