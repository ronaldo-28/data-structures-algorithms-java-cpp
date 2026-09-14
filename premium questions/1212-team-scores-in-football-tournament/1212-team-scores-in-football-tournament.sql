# Write your MySQL query statement below
WITH points AS (
    SELECT
        host_team AS team_id,
        CASE 
            WHEN host_goals > guest_goals THEN 3
            WHEN host_goals = guest_goals THEN 1
            ELSE 0
        END AS points
    FROM Matches

        UNION ALL

    SELECT
        guest_team,
        CASE 
            WHEN host_goals < guest_goals THEN 3
            WHEN host_goals = guest_goals THEN 1
            ELSE 0
        END
    FROM Matches
)
SELECT
    t.team_id,
    t.team_name,
    COALESCE(SUM(p.points), 0) AS num_points
FROM Teams t
LEFT JOIN points p
    ON t.team_id = p.team_id
GROUP BY 1, 2
ORDER BY 3 DESC, 1;
