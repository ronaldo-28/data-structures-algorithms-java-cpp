# Write your MySQL query statement below
WITH consecutives AS(
  SELECT 
    C.seat_id, 
    C.free, 
    SUM(C.free) OVER(ORDER BY C.seat_id)  AS cumulative_sum, 
    ROW_NUMBER() OVER(ORDER BY C.seat_id) AS row_num 
  FROM 
    Cinema AS C
), 
groupings AS(
  SELECT 
    MIN(C.seat_id)                      AS first_seat_id, 
    MAX(C.seat_id)                      AS last_seat_id, 
    MAX(C.seat_id) - MIN(C.seat_id) + 1 AS consecutive_seats_len 
  FROM 
    consecutives AS C
  WHERE 
    C.free = 1 
  GROUP BY 
    C.row_num - C.cumulative_sum
) 
SELECT 
  G.first_seat_id, 
  G.last_seat_id, 
  G.consecutive_seats_len 
FROM 
  groupings AS G
WHERE 
  G.consecutive_seats_len = (
    SELECT 
      MAX(consecutive_seats_len) 
    FROM 
      groupings
  ) 
ORDER BY 
  G.first_seat_id