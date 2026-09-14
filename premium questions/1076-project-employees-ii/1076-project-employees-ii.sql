# Write your MySQL query statement below
SELECT 
    project_id
FROM 
    Project
GROUP BY 
    project_id
HAVING 
    COUNT(*) = (SELECT COUNT(*) c
        FROM Project
        GROUP BY project_id
        ORDER BY c DESC
        LIMIT 1);