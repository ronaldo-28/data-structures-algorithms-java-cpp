# Write your MySQL query statement below
WITH excellent_student_counts AS (
  SELECT
    (SELECT COUNT(*) FROM NewYork WHERE score >= 90) AS ny,
    (SELECT COUNT(*) FROM California WHERE score >= 90) AS ca
)
SELECT
  (CASE
    WHEN E.ny > E.ca THEN 'New York University'
    WHEN E.ny < E.ca THEN 'California University'
    ELSE 'No Winner'
  END) AS winner
FROM
  excellent_student_counts E;