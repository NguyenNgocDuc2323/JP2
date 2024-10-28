package Service;

import Entity.Department;
import Entity.Employee;
import IGeneral.IGeneric;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService implements IGeneric {
    private List<Employee> employees;
    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }
    @Override
    public Map<String, List<Employee>> searchDepartmentByName(String departmentName) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().getName().equals(departmentName))
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getName()));
    }

    @Override
    public Map<String, List<Employee>> groupByDepartmentName() {
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getName()));
    }


    @Override
    public Map<String, Long> countTotalEmployeesByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.counting()
                ));
    }



}
