# Write your MySQL query statement below
SELECT
    candidate_id
FROM Candidates
GROUP BY 1
HAVING SUM(
    CASE
        WHEN skill IN ('Python', 'Tableau', 'PostgreSQL') THEN 1
        ELSE 0
    END
) = 3
ORDER BY 1
