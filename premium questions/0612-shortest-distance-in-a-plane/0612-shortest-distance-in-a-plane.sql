# Write your MySQL query statement below

with point2d2 as (
    select *,
        row_number() over(order by x) as id
    from
        point2d
)

select
    round(min(power(power(t1.x - t2.x, 2) + power(t1.y - t2.y, 2), 1/2)), 2)
    as shortest
from
    point2d2 t1, point2d2 t2
where
    t1.id <> t2.id;