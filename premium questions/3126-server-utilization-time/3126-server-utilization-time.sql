# Write your MySQL query statement below
# servers: server_id, status_time, session_status
select floor(sum(case when session_status='start' then -unix_timestamp(status_time) else unix_timestamp(status_time) end)/(60*60*24)) as total_uptime_days
from servers