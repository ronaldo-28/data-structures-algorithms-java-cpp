# Write your MySQL query statement below
with friendship as(
    select user1,user2  from friends
    union 
    select user2 ,user1 from friends
)
, users as(
    select distinct user1 from friends
    union
    select distinct user2 from friends
)
select  user1 , round(count(*)*100/(select count(*) from users),2)percentage_popularity 
 from friendship
group by 1 
order by 1