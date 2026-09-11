# Write your MySQL query statement below
with cta as(
    select pass_from give, a.team_name  t1,time_stamp 
    ,b.team_name t2,pass_to  gett,
    row_number() over(partition by a.team_name  order by time_stamp )goal,
        row_number() over(partition by a.team_name,a.team_name =b.team_name   order by time_stamp )rnk
        from passes
        join teams a
        on a.player_id =pass_from 
        join teams b
        on pass_to=b.player_id
),
cte as(
    select t1, count(*)streak,
    row_number() over(partition by t1 order by count(*)desc)rn
    from cta
    where t1=t2
    group by 1,goal-rnk
)
select t1 team_name ,streak longest_streak 
from cte
where rn=1
order by 1