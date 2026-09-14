# Write your MySQL query statement below
WITH RECURSIVE merge AS
(SELECT hall_id, start_day, end_day
    FROM HallEvents
            UNION 
SELECT x.hall_id, y.start_day, GREATEST(x.end_day, y.end_day) 
    FROM HallEvents x
    JOIN merge y ON x.hall_id = y.hall_id AND x.start_day > y.start_day 
                AND x.start_day BETWEEN y.start_day and y.end_day 
                AND (x.start_day, x.end_day) != (y.start_day, y.end_day) ),


 final_merge AS
(SELECT hall_id, start_day, end_day, 
        ROW_NUMBER() OVER (PARTITION BY hall_id ORDER BY start_day, end_day DESC) AS rn
    FROM merge
    GROUP BY hall_id, start_day, end_day ) 


SELECT hall_id, start_day, end_day
    FROM final_merge
       EXCEPT 
SELECT y.hall_id, y.start_day, y.end_day
    FROM final_merge x
    JOIN final_merge y ON y.hall_id = x.hall_id AND y.rn > x.rn 
                      AND y.start_day BETWEEN x.start_day AND x.end_day

    ORDER BY hall_id, start_day

