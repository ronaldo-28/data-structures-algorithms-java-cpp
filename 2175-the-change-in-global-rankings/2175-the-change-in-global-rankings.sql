# Write your MySQL query statement below
WITH initial_rank AS (
    SELECT
        team_id,
        name,
        RANK() OVER(ORDER BY points DESC, name) AS initial_rn
    FROM TeamPoints
),
final_points AS (
    SELECT
        tp.team_id AS team_id,
        tp.name AS name,
        tp.points + pc.points_change AS final
    FROM TeamPoints tp
    JOIN PointsChange pc
        ON tp.team_id = pc.team_id
),
final_rank AS (
    SELECT
        team_id,
        name,
        RANK() OVER(ORDER BY final DESC, name) AS final_rn
    FROM final_points
)
SELECT
    ir.team_id,
    ir.name,
    CAST(ir.initial_rn AS SIGNED) - CAST(fr.final_rn AS SIGNED)
     AS rank_diff
FROM initial_rank ir
JOIN final_rank fr
    ON ir.team_id = fr.team_id
;

