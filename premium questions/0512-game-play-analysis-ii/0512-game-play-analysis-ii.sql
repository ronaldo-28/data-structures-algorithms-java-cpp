# Write your MySQL query statement below
SELECT a1.player_id, a1.device_id
FROM Activity a1
inner join
(
  select player_id , MIN(event_date) event_date
  from Activity
  group by player_id
) a2
  on a1.player_id = a2.player_id
  and a1.event_date = a2.event_date