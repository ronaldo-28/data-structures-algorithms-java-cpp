# Write your MySQL query statement below

select 
    p1.id as P1, 
    p2.id as P2, 
    abs(p1.x_value - p2.x_value) * abs(p1.y_value - p2.y_value) as AREA
from points as p1
join points as p2
on p1.x_value != p2.x_value
and p1.y_value != p2.y_value
and p1.id < p2.id 
order by AREA desc, P1, P2