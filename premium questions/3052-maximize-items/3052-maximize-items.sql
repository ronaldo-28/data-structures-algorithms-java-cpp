# Write your MySQL query statement below
WITH prime_items AS (
    SELECT 
        item_type,
        COUNT(*) AS prime_items_count,
        SUM(square_footage) AS prime_items_space,
        FLOOR(500000 / SUM(square_footage)) AS prime_item_combinations
    FROM Inventory
    WHERE item_type = 'prime_eligible'
    GROUP BY 1
),

not_prime_items AS (
    SELECT 
        item_type,
        COUNT(*) AS not_prime_items_count,
        SUM(square_footage) AS not_prime_items_space
    FROM Inventory
    WHERE item_type = 'not_prime'
    GROUP BY 1
)

SELECT
    'prime_eligible' AS item_type,
    prime_item_combinations * prime_items_count AS item_count
FROM prime_items

        UNION ALL

SELECT
    'not_prime' AS item_type,
    COALESCE(
        FLOOR(
            (500000 - prime_item_combinations * prime_items_space)
            / not_prime_items_space
        ) * not_prime_items_count
    , 0) AS item_count
FROM prime_items
CROSS JOIN not_prime_items

ORDER BY item_count DESC
