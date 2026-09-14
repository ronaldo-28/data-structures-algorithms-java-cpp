# Write your MySQL query statement below
WITH metals AS (
    SELECT
        symbol AS metal
    FROM Elements
    WHERE type = 'Metal'
),

non_metals AS (
    SELECT
        symbol AS nonmetal
    FROM Elements
    WHERE type = 'Nonmetal'
)

SELECT
    m.metal,
    n.nonmetal
FROM metals m
CROSS JOIN non_metals n