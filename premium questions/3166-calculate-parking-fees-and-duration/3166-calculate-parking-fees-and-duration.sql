# Write your MySQL query statement below
with park_sumry as(
    select car_id ,lot_id ,
sum(timestampdiff(minute,entry_time,exit_time)/60)tph,
    sum(fee_paid)amt
    from ParkingTransactions 
    group by 1,2
),
lot_sumry as(
    select car_id ,lot_id most_time_lot ,
    row_number() over(partition by car_id order by tph desc)rnk
    from park_sumry
)
select car_id,sum(amt)total_fee_paid ,
round(sum(amt)/sum(tph),2)avg_hourly_fee ,
most_time_lot
from park_sumry
join lot_sumry
using(car_id)
where rnk=1
group by 1,4
order by 1