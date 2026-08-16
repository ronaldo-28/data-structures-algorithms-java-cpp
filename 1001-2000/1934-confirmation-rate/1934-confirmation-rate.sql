select u.user_id, round(ifnull(sum(c.action = 'confirmed')/count(c.action),0),2) as confirmation_rate
from Signups u
left join Confirmations c
on u.user_id = c.user_id
group by u.user_id;