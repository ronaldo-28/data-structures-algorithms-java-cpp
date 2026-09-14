# Write your MySQL query statement below
SELECT
    a.sale_date,
    a.sold_num - o.sold_num AS diff
FROM Sales a
JOIN (
    SELECT
        sale_date,
        sold_num
    FROM Sales
    WHERE fruit = 'oranges'
) o
    ON a.sale_date = o.sale_date 
WHERE fruit = 'apples'