# Write your MySQL query statement below
select u.name, sum(t.amount) as balance
from users u
left join transactions t 
on u.account = t.account 
group by name
having balance > 10000;