# Write your MySQL query statement below
-- cold = avg(weather_state) <= 15
-- hot = avg(weather_state) > 25
-- else Warm
select
c.country_name,
case
    when avg(weather_state) <= 15 then 'Cold'
    when avg(weather_state) >= 25 then 'Hot'
    else 'Warm'
end as weather_type
from Countries c
left join Weather w
on c.country_id = w.country_id
WHERE w.day >= '2019-11-01'
  AND w.day < '2019-12-01'
group by c.country_id;