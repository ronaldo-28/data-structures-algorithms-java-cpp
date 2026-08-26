# Write your MySQL query statement below
select c.customer_id, c.customer_name
from Orders o left join Customers c
on c.customer_id = o.customer_id
group by 1,2
having sum(product_name='A') >=1
and sum(product_name='B') >=1
and sum(product_name='C') = 0