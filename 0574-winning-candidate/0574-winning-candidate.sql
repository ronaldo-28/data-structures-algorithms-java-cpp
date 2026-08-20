# Write your MySQL query statement below
SELECT distinct name
FROM Candidate c
LEFT JOIN Vote v ON v.candidateId = c.id
GROUP BY 1
ORDER BY count(v.id) desc
LIMIT 1