# Write your MySQL query statement below

select 
    concat(dayname(day), ', ', monthname(day), ' ', day(day), ', ', year(day)) as day 
from days 