# Write your MySQL query statement below

with emp_sal_rk as
(
    select emp_id,
    dept,
    dense_rank() over (partition by dept order by salary desc) as dept_sal_rk
    from 
    employees
)
select emp_id,
dept
from emp_sal_rk
where dept_sal_rk = 2
order by 1