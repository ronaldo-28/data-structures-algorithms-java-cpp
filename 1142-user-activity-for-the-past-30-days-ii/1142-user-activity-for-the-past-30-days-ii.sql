# Write your MySQL query statement below

with t as (
    select 
        user_id, 
        session_id 
    from Activity 
    where activity_date between "2019-06-28" and "2019-07-27"
    group by user_id, session_id
), 

t1 as (
    select 
        count(session_id) as counts 
    from t 
    group by user_id
)

select
    ifnull(round(avg(counts), 2), 0) as average_sessions_per_user 
from t1 


