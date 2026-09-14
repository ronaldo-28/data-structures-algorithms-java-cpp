# Write your MySQL query statement below
select team_name ,count(*)matches_played ,
sum(if( team_id  =home_team_id and home_team_goals >away_team_goals  , 3,
        if( team_id  =away_team_id and home_team_goals <away_team_goals  , 3,
        if( home_team_goals =away_team_goals , 1 , 0)))
        
)points ,
sum(if( team_id =home_team_id  , home_team_goals , away_team_goals ))goal_for ,
sum(if( team_id =home_team_id   , away_team_goals , home_team_goals ))goal_against ,
sum(if( team_id =home_team_id  , home_team_goals - away_team_goals 
,   away_team_goals - home_team_goals  ))goal_diff 
from Teams 
join Matches 
on team_id in(home_team_id,away_team_id)
group by 1
order by 3 desc,6 desc,1