# Write your MySQL query statement below
SELECT
    t1.team_name,

    CASE 
        WHEN p.time_stamp <= '45:00' THEN 1
        ELSE 2
    END AS half_number,

    SUM(
        CASE
            WHEN t1.team_name = t2.team_name THEN 1
            ELSE -1
        END
    ) AS dominance
    
FROM Teams t1

JOIN Passes p ON t1.player_id = p.pass_from

JOIN Teams t2 ON t2.player_id = p.pass_to

GROUP BY 1, 2
ORDER BY 1, 2
