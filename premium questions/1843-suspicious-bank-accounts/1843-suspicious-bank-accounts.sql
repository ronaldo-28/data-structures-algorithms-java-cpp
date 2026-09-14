# Write your MySQL query statement below
with cte as(
    select account_id ,date_format(day,'%y%m') tym,
    sum(amount) amt,max_income 
    from Accounts 
    join Transactions 
    using(account_id )
    where type='Creditor'
    group by 1,2
    having amt>max_income
)
select distinct account_id 
from cte a
join cte b
using(account_id )
where period_diff(a.tym,b.tym)=1