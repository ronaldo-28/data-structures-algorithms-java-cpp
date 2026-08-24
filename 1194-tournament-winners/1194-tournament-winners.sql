# Write your MySQL query statement below
select	group_id, player_id from
	(	select 	p.group_id, p.player_id,
		rank() over (partition by p.group_id order by
		sum(case when p.player_id = m.first_player then m.first_score else m.second_score end) desc,
		p.player_id asc) rk
	from players p	join matches m on
		p.player_id in (m.first_player, m.second_player)
	group by p.group_id, p.player_id
	)  t where rk = 1