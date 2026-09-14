# Write your MySQL query statement below
select
concat(a.topping_name,',',b.topping_name,',',c.topping_name)pizza ,
a.cost+b.cost+c.cost total_cost 
from Toppings a
join Toppings b
on a.topping_name<b.topping_name
join Toppings c
on b.topping_name<c.topping_name
order by 2 desc,1
