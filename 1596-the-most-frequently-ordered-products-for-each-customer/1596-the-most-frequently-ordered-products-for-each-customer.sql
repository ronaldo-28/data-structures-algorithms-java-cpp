# Write your MySQL query statement below
with total_order_count as(
    select customer_id, product_id, count(*) as order_count
    from Orders
    group by 1,2
),
ranked as (select customer_id, product_id,
DENSE_RANK() OVER(PARTITION BY customer_id order by order_count desc) as rk
from total_order_count)

select r.customer_id, r.product_id, product_name from ranked r join Products p on p.product_id = r.product_id
where rk = 1