# Write your MySQL query statement below
Select c.patient_id ,p.patient_name, p.age,
 DATEDIFF(Min(c1.test_date),MIN(c.test_date)) as recovery_time 
from 
covid_tests c inner join covid_tests c1
 on c.patient_id = c1.patient_id
 and c.test_date < c1.test_date
 and c.result = 'Positive' 
 and c1.result = 'Negative' 
 join patients p on  p.patient_id = c.patient_id 
 group by p.patient_id 
 order by recovery_time ,patient_name ;

