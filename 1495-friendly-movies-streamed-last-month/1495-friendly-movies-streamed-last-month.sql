# Write your MySQL query statement below
SELECT
    DISTINCT c.title AS title
FROM Content c
JOIN TVProgram p
    ON c.content_id = p.content_id
WHERE c.Kids_content = "Y" AND 
    c.content_type = 'Movies' AND 
    p.program_date LIKE '2020-06%'