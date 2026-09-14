# Write your MySQL query statement below
SELECT
    SUM(
        GREATEST(
            LEAST(max_left, max_right) - height, 0
        )
    ) AS total_trapped_water
FROM (
    SELECT
        *,
        MAX(height) OVER(ORDER BY id) AS max_left,
        MAX(height) OVER(ORDER BY id DESC) AS max_right
    FROM Heights
    ORDER BY id
) AS c

    