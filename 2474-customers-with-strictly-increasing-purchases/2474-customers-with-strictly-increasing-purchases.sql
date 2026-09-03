# Write your MySQL query statement below
with cte as(
    select customer_id,year(order_date)-rank() over(partition by customer_id order by sum(price) )rnk
    from orders
    group by 1,year(order_date)
)
select customer_id
from cte
group by 1
having count(distinct rnk)=1