# Write your MySQL query statement below
with cte as(select *,
row_number() over(partition by username    order by startDate desc )rnk,
count(username) over(partition by username)cnt
from UserActivity )
select username ,activity ,startDate  ,endDate    
from cte
where rnk=2
or cnt =1