# Write your MySQL query statement below
SELECT DISTINCT t1.N, CASE WHEN t1.P IS NULL THEN 'Root' WHEN t2.P IS NULL THEN 'Leaf' ELSE 'Inner' END AS Type
FROM Tree t1
LEFT JOIN Tree t2 ON t1.N = t2.P
ORDER BY N;