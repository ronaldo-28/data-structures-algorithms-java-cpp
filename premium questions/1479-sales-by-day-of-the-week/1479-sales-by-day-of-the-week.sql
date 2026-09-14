# Write your MySQL query statement below
SELECT
    i.item_category AS CATEGORY,
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 2
                THEN o.quantity
            END
        )
    , 0) AS 'MONDAY',
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 3
                THEN o.quantity
            END 
        )
    , 0) AS 'TUESDAY',
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 4
                THEN o.quantity
            END
        )
    , 0) AS 'WEDNESDAY',
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 5
                THEN o.quantity
        END 
        )
    , 0) AS 'THURSDAY',
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 6
                THEN o.quantity
            END
        ) 
    , 0) AS 'FRIDAY',
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 7
                THEN o.quantity
            END 
        )
    , 0) AS 'SATURDAY',
    COALESCE(
        SUM(
            CASE 
                WHEN DAYOFWEEK(order_date) = 1
                THEN o.quantity
            END
        ) 
    , 0) AS 'SUNDAY'
FROM Items i
LEFT OUTER JOIN Orders o
    ON o.item_id = i.item_id
GROUP BY 1
ORDER BY 1;