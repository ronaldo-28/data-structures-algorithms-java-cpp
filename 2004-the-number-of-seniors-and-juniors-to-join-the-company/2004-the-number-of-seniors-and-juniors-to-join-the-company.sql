# Write your MySQL query statement below
with senior as(
    select employee_id ,sum(salary) over(order by salary ) amt
    from Candidates 
    where experience='Senior'
),
Junior as(
    select employee_id ,sum(salary) over(order by salary ) amt
    from Candidates 
    where experience='Junior'
),
sen as(
    select count(employee_id ) cnt,max(amt)amt
    from senior
    where amt<=70000
),jun as(
    select count(employee_id ) cnt
    from junior
    where amt<=(70000-(select ifnull(amt,0) from sen)
))
select 'Senior' experience ,(select cnt from sen) accepted_candidates 
union all
select 'Junior' experience ,(select cnt from jun) accepted_candidates 
 