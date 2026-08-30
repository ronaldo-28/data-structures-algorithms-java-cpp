# Write your MySQL query statement below
with cte as(select a.user_id  user1_id ,b.user_id user2_id ,
count(*)cnt
from Relations a
join Relations b
on a.follower_id =b.follower_id 
and a.user_id<b.user_id
group by 1,2)
select user1_id ,user2_id
from cte
where cnt=(select max(cnt)from cte )