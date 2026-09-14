# Write your MySQL query statement below
with cte as (
select
    user1_id as user_id,
    page_id
from Friendship f
left join Likes l on f.user2_id = l.user_id
union all
select
    user2_id as user_id,
    page_id
from Friendship f
left join Likes l on f.user1_id = l.user_id
)

select
    cte.user_id,
    cte.page_id,
    count(*) as friends_likes
from cte
left join Likes l on cte.user_id = l.user_id and cte.page_id = l.page_id
where cte.page_id is not null
 and l.page_id is null
group by 1, 2
order by 1