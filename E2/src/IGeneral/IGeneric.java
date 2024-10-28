package IGeneral;

import Entity.Department;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface IGeneric<T>{
    public Map<String,T> searchDepartmentByName(String departmentName);
    public Map<String,T> groupByDepartmentName();
    public Map<String, Integer> countTotalEmployeesByDepartment();

}
