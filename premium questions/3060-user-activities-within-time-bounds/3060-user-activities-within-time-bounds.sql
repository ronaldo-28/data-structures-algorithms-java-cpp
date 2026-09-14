# Write your MySQL query statement below
select distinct a.user_id 
from Sessions a
join Sessions b
on a.user_id =b.user_id 
and a.session_type =b.session_type 
and a.session_start<b.session_start
and timestampdiff(hour,a.session_end,b.session_start)<13
order by 1