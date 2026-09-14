# Write your MySQL query statement below

with t as (
    select 
        user_id, 
        s.product_id, 
        sum(quantity) * price as sales  
    from sales as s
    join product as p
    on s.product_id = p.product_id
    group by user_id, s.product_id
), 

t1 as (
    select 
        user_id, 
        max(sales) as mx 
    from t
    group by user_id
)

select
    user_id, 
    product_id
from t
where (user_id, sales) in (select user_id, mx from t1)



