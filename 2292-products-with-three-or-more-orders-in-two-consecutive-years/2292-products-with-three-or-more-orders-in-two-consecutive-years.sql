# Write your MySQL query statement below

with t as (
    select 
        product_id, 
        year(purchase_date) as year
    from orders
    group by product_id , year(purchase_date)
    having count(*) >= 3
)

select 
    distinct t.product_id
from t 
join t as t1
on t.product_id = t1.product_id
and abs(t.year - t1.year) = 1 


