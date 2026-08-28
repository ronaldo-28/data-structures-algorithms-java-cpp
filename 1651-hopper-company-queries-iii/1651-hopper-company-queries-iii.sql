# Write your MySQL query statement below
with recursive month as (
    select 1 as month
    union
    select month + 1 as month from month where month < 12
)

, month_rides as(
    select   
        MONTH(r.requested_at) as month,
        sum(a.ride_distance) as ride_distance,
        sum(a.ride_duration) as ride_duration
    from Rides r join AcceptedRides a on a.ride_id = r.ride_id
    where YEAR(r.requested_at) = '2020'
    group by month
    # order by month asc
)
, every_month_rides as(
    select 
        m.month, 
        ifnull(mr.ride_distance,0) as ride_distance, 
        ifnull(mr.ride_duration,0) as ride_duration
    from month m left join month_rides mr on mr.month = m.month
    # order by 1
)


# select * from month
# select * from month_rides
# select * from every_month_rides

select 
    t1.month as month,
    round((t1.ride_distance + t2.ride_distance + t3.ride_distance)/3,2) as average_ride_distance,
    round((t1.ride_duration + t2.ride_duration + t3.ride_duration)/3,2) as average_ride_duration
from every_month_rides t1
join every_month_rides t2
join every_month_rides t3 
on t1.month = t2.month -1 and t2.month = t3.month -1