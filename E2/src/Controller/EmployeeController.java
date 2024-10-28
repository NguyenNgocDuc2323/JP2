package Controller;

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
}
