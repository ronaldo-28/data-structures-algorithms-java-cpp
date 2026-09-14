# Write your MySQL query statement below
SELECT 
  M.member_id, 
  name, 
  CASE 
    WHEN COUNT(V.visit_id) = 0 THEN 'Bronze'
    WHEN IFNULL(COUNT(DISTINCT P.visit_id),0)*100/COUNT(V.visit_id) >= 80 THEN 'Diamond'
    WHEN IFNULL(COUNT(DISTINCT P.visit_id),0)*100/COUNT(V.visit_id) >= 50 THEN 'Gold'
    ELSE 'Silver'
  END AS category
FROM Members M 
LEFT JOIN Visits V ON M.member_id = V.member_id
LEFT JOIN Purchases P ON V.visit_id = P.visit_id
GROUP BY M.member_id, name
ORDER BY 1 ASC