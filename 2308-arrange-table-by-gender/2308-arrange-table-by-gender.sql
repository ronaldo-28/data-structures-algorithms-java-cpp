# Write your MySQL query statement below
with cte as(select *,
case when gender='female' then 1 
when gender='other' then 2
else 3 
end gender_rnk,
rank() over(partition by gender order by user_id )rnk
from Genders )
select user_id,
gender
from cte
order by rnk,gender_rnk