# Write your MySQL query statement below
# Write your MySQL query statement below
select date_format(order_date ,'%Y-%m') month  ,
count(order_date ) order_count ,
count(distinct customer_id )customer_count 


from Orders 
where  invoice    >20
group by 1