# Write your MySQL query statement below
SELECT 'bull' AS word, SUM(content LIKE '% bull %') AS count
FROM Files
UNION ALL
SELECT 'bear' AS word, SUM(content LIKE '% bear %') AS count
FROM Files