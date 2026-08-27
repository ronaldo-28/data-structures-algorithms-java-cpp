# Write your MySQL query statement below
with recursive cte as(
    select 1 as customer_id
    union all
    select customer_id+1
    from cte 
where customer_id<(select max(customer_id ) from customers)
)
select customer_id ids
from cte
where customer_id not in(select customer_id from customers)
order by 1