# Write your MySQL query statement below
WITH weightage AS (
    SELECT
        voter,
        CASE
            WHEN candidate IS NULL 
            THEN 0 ELSE 1
        END / COUNT(DISTINCT candidate)
        AS split
    FROM Votes
    GROUP BY 1
),

ranking AS (
    SELECT
        v.candidate,
        SUM(w.split) AS total,
        RANK() OVER(ORDER BY SUM(w.split) DESC) AS rn
    FROM Votes v
    LEFT JOIN weightage w
        ON v.voter = w.voter
    GROUP BY v.candidate
    ORDER BY total DESC, v.candidate 
)

SELECT
    candidate
FROM ranking
WHERE rn = 1