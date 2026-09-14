/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    private Employee[] db = new Employee[2001];
    private int findImp(int id){
        Employee emp = db[id];
        int importance = emp.importance;
        for (int subId: emp.subordinates) importance += findImp(subId);
        return importance;
    }
    public int getImportance(List<Employee> employees, int id) {
        for (Employee emp: employees) db[emp.id] = emp;
        return findImp(id);        
    }
}