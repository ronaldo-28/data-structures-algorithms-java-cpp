# Write your MySQL query statement below

with store1 as (
    select 
        product_id, 
        price as store1
    from Products 
    where store = 'store1'
), 

store2 as (
    select 
        product_id, 
        price as store2
    from Products 
    where store = 'store2'
), 

store3 as (
    select 
        product_id, 
        price as store3
    from Products 
    where store = 'store3'
), 

distinct_products as (
    select 
        distinct product_id
    from products
)

select 
    p.product_id, 
    store1,
    store2,
    store3
from distinct_products as p
left join store1 as s1
on p.product_id = s1.product_id
left join store2 as s2
on p.product_id = s2.product_id
left join store3 as s3
on p.product_id = s3.product_id

