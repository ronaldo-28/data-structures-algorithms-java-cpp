# Write your MySQL query statement below

with t as (
    select 
        salary
    from employees 
    group by salary 
    having count(*) >= 2 
), 

t1 as (
    select 
        row_number() over (order by salary) as n, 
        salary
    from t
)

select
    employee_id, 
    name, 
    t1.salary, 
    n as team_id
from t1
join employees as e 
on t1.salary = e.salary
order by team_id, employee_id



