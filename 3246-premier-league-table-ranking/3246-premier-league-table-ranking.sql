# Write your MySQL query statement below
WITH CTE AS (
SELECT team_id, team_name, 3*wins+1*draws as points FROM TeamStats
Order by points desc, team_name asc
),

CTE1 AS (
SELECT team_id, team_name, points, RANK() OVER (ORDER BY points DESC) as position
FROM CTE
)

SELECT * FROM CTE1