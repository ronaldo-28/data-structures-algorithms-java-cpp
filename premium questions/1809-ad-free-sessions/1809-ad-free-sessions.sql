# Write your MySQL query statement below
# Write your MySQL query statement below
select session_id 
from playback where session_id not in
(select distinct session_id
from playback p join ads a on p.customer_id = a.customer_id 
where start_time <= timestamp and end_time >= timestamp)