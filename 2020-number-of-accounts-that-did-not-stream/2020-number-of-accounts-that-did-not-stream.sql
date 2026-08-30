# Write your MySQL query statement below
select count(su.account_id) as accounts_count
from Subscriptions su
    left join Streams st on st.account_id = su.account_id
        and year(st.stream_date) = 2021
where 2021 between year(su.start_date) and year(su.end_date)
    and st.session_id is null