# Write your MySQL query statement below
WITH winner_list AS (
    -- unpivot (columns to rows)
    SELECT Wimbledon AS player_id
    FROM Championships
        UNION ALL
    SELECT Fr_open
    FROM Championships
        UNION ALL
    SELECT US_open
    FROM Championships
        UNION ALL
    SELECT Au_open
    FROM Championships
)
SELECT
    w.player_id,
    p.player_name,
    COUNT(*) AS grand_slams_count
FROM winner_list w
JOIN Players p
    ON p.player_id = w.player_id
GROUP BY 1, 2
HAVING COUNT(*) > 0

