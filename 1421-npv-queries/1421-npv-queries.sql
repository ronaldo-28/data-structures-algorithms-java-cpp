# Write your MySQL query statement below
select

q.id,
q.year,
coalesce(n.npv,0) as npv

from Queries q
left join NPV n
on n.id = q.id
AND n.year = q.year;