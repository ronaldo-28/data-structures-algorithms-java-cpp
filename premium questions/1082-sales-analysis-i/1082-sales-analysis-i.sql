# Write your MySQL query statement below
select seller_id
from (
    select seller_id, RANK() OVER (Order By sum(price) DESC) as seller_rank
    from Sales
    Group By seller_id
    )as Sales_details
Where seller_rank =1