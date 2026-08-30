# Write your MySQL query statement below

with t as (
    select
        candidate_id, 
        interview_id 
    from candidates 
    where years_of_exp >= 2 
), 

t1 as (
    select 
        interview_id 
    from rounds 
    group by interview_id
    having sum(score) > 15
)

select 
    candidate_id
from t
join t1
on t.interview_id = t1.interview_id

