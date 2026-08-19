# Write your MySQL query statement below
 SELECT a1.player_id, a1.event_date,
 (SELECT sum(a2.games_played) FROM activity a2
 WHERE a1.player_id = a2.player_id AND a1.event_date >=a2.event_date
 GROUP BY a1.player_id)
 AS games_played_so_far
 FROM activity a1 ORDER BY a1.player_id, games_played_so_far;