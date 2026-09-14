# Write your MySQL query statement below
select
	s.buyer_id
from 
	Product p join Sales s
on  
	p.product_id = s.product_id
group by
	s.buyer_id 
having 
	sum(p.product_name = 'S8') > 0 AND sum(p.product_name = 'iPhone') = 0