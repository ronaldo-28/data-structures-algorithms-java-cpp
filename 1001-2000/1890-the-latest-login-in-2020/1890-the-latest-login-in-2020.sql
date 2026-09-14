# Write your MySQL query statement below
select user_id, max(time_stamp) as last_stamp from logins
where YEAR(time_stamp) = 2020
group by 1;