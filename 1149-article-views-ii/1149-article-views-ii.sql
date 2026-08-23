# Write your MySQL query statement below
# Code Author Naveen Kumar Vadlamudi 
# Simplest Solution using GROUP By + HAVING + DISTINCT 
# Upvote If it Helps  👍👍

SELECT 
DISTINCT VIEWER_ID AS ID
FROM VIEWS
GROUP BY VIEW_DATE, VIEWER_ID
HAVING COUNT(DISTINCT ARTICLE_ID) >=  2
ORDER BY ID 