# Write your MySQL query statement below
Select v.customer_id , count(customer_id ) as count_no_trans 
From Visits As v
left join Transactions As t
On v.visit_id = t.visit_id 
where t.transaction_id is null
group by v.customer_id 