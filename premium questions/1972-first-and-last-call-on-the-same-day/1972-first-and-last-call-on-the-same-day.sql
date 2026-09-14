# Write your MySQL query statement below
with all_Callers as (
select caller_id user_id, recipient_id as reciever_id  , call_time from Calls
union 
select recipient_id v, caller_id as  reciever_id ,call_time from Calls
)

,first_last_caller as ( 
select
distinct
user_id ,
first_value(reciever_id) over (partition by user_id,date(call_time) order by call_time) first_recipient_id,
first_value(reciever_id) over (partition by user_id,date(call_time) order by  call_time desc) last_recipient_id   
from all_Callers
)

select 
user_id
from
first_last_caller
where first_recipient_id = last_recipient_id
group by user_id