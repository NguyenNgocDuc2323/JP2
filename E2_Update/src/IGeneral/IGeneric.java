package IGeneral;

import Entity.Department;
import Entity.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGeneric<T>{
    public Map<String,Set<T>> searchDepartmentByName(String departmentName);
    public Map<String,Set<T>> groupByDepartmentName();
    public Map<String, Integer> countTotalEmployeesByDepartment();
    public Map<String, Long> countMaleEmployees();
    public Map<String, Set<T>> countToMonthEmployees();
}
