package Controller;

import Entity.Department;
import Entity.Employee;
import Service.EmployeeService;

import java.util.*;

public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Map<String, List<Employee>> searchEmployee(String departmentName) {
        Map<String, List<Employee>> foundEmployees = employeeService.searchDepartmentByName(departmentName);
        return foundEmployees;
    }
    public Map<String, List<Employee>> groupByDepartmentName(){
        Map<String, List<Employee>> foundEmployees = employeeService.groupByDepartmentName();
        return foundEmployees;
    }
    public Map<String, Long> countTotalEmployeesByDepartment(){
        Map<String, Long> foundEmployees = employeeService.countTotalEmployeesByDepartment();
        return foundEmployees;
    }
    public Map<String,Long> countMaleEmployees(List<Department> departments){
        Map<String, Long> foundEmployees = employeeService.countMaleEmployees();
        return foundEmployees;
    }
    public Map<String,Set<Employee>> countToMonthEmployees(){
        Map<String, Set<Employee>> foundEmployees = employeeService.countToMonthEmployees();
        return foundEmployees;
    }
}
