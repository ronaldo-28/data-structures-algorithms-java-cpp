# Write your MySQL query statement below
with cte as(select team_id,team_name,
wins*3+draws*1 as points from TeamStats),

 cte2 as(select *,
rank() over(order by points desc) as position,
count(*) over() as total_teams
from cte)

select team_name,points,position,
case when position<=ceil(total_teams/3.0) then 'Tier 1'
when position<=ceil(2*total_teams/3.0) then 'Tier 2'
else 'Tier 3'
end as tier
from cte2
order by points desc,team_name