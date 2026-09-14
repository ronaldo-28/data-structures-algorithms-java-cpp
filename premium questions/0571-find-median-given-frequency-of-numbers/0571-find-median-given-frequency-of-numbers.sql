# Write your MySQL query statement below
SELECT 
	ROUND(AVG(num*1.0),2) AS median
FROM (
    SELECT
        *,
        SUM(frequency) OVER (ORDER BY num ASC) AS accumulated_sum,
        SUM(frequency) OVER () / 2 as medium_num
    FROM 
        Numbers
) AS TEMP
WHERE 
	accumulated_sum - frequency <= medium_num AND accumulated_sum >= medium_num
	# The accumulated frequency of the previous num should be smaller or equal to medium_num