# Write your MySQL query statement below
WITH CTE AS (
        Select *, count(*) over(partition by user_id)/4 as avg_weekly_posts
        from Posts
        where post_date between '2024-02-01' and '2024-02-28'
),

CTE2 AS (
    Select *, count(post_id) over(partition by user_id order by post_date range between interval 6 day preceding and current row) as max_7day_posts 
    from CTE
)



select distinct user_id , max(max_7day_posts) as max_7day_posts , avg_weekly_posts 
from CTE2
where max_7day_posts >=2*avg_weekly_posts 
group by 1
order by 1