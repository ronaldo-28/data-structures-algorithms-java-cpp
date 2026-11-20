# Write your MySQL query statement below
with three_plus as (
    select store_id
from inventory
group by store_id
having count(product_name)>=3

),
price_extreme as(
select i.store_id, max(i.price) as most_expensive, min(i.price) as cheapest
from inventory as i
join three_plus as t on i.store_id = t.store_id
group by i.store_id
),
inter as (select i.store_id, 
case when i.price = p.most_expensive then product_name end as most_exp_product, 
CASE WHEN i.price = p.most_expensive THEN quantity END AS most_exp_quantity,
case when i.price = p.cheapest then product_name end as cheapest_product,
case when i.price = p.cheapest then quantity end as cheapest_quantity
from inventory as i
join price_extreme p
on p.store_id = i.store_id
),
results as (
    select i.store_id, s.store_name, s.location, 
    max(i.most_exp_product)as most_exp_product , 
    max(i.cheapest_product) as cheapest_product,
    MAX(i.most_exp_quantity) AS most_exp_quantity,
    MAX(i.cheapest_quantity) AS cheapest_quantity
from inter i
left join stores s on i.store_id = s.store_id
group by i.store_id
)
SELECT store_id,
       store_name,
       location,
       most_exp_product,
       cheapest_product,
       ROUND(1.0 * cheapest_quantity / NULLIF(most_exp_quantity, 0), 2) AS imbalance_ratio
FROM results
WHERE most_exp_quantity < cheapest_quantity
ORDER BY imbalance_ratio DESC, store_name ASC;