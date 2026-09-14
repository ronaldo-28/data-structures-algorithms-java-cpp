# Write your MySQL query statement below
/* Write your T-SQL query statement below */


select order_id
from OrdersDetails 
group by  order_id 
having max(quantity) > all(
select avg(quantity*1.0)
from OrdersDetails  
group by  order_id ) 