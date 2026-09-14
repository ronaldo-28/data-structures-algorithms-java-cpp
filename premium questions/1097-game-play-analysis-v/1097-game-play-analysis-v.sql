# Write your MySQL query statement below
with fdl as(
    select player_id ,min(event_date) fd
    from Activity 
    group by 1
),
sdl as(
    select b.player_id, fd, a.event_date sd
    from Activity a
    right join fdl b
    on a.player_id=b.player_id
    and     timestampdiff(day,fd,a.event_date)=1
)
select fd install_dt ,count(player_id) installs ,
round(1.0*count(sd)/count(player_id),2)Day1_retention 
from sdl
group by 1