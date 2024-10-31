package Service;

import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import IGeneral.IGeneric;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService implements IGeneric {
    private List<Employee> employees;
    private List<Department> departments;

    public EmployeeService(List<Employee> employees, List<Department> departments) {
        this.employees = employees;
        this.departments = departments;
    }
    public Map<String, List<Employee>> searchDepartmentByName(String departmentName) {
        Map<String, List<Employee>> departmentEmployees = new HashMap<>();

        departments.stream()
                .filter(department -> department.getName().equalsIgnoreCase(departmentName))
                .forEach(department -> {
                    List<Employee> employeesInDept = employees.stream()
                            .filter(employee -> employee.getDepartment().getId() == department.getId())
                            .collect(Collectors.toList());
                    departmentEmployees.put(department.getName(), employeesInDept);
                });
        return departmentEmployees;
    }

    @Override
    public Map<String, List<Employee>> groupByDepartmentName() {
        Map<String, List<Employee>> departmentEmployees = new HashMap<>();

        employees.stream().forEach(employee -> {
            String departmentCode = employee.getDepartment().getName();
            departmentEmployees.putIfAbsent(departmentCode, new ArrayList<>());
            departmentEmployees.get(departmentCode).add(employee);
        });
        return departmentEmployees;
    }


    @Override
    public Map<String, Long> countTotalEmployeesByDepartment() {
        Map<String,Long> countEmployees = new HashMap<>();
        departments.stream()
                .forEach(department -> {
                    long totalEmp = employees.stream()
                            .filter(employee -> employee.getDepartment().getId() == department.getId())
                            .collect(Collectors.toSet())
                            .stream().count();
                    countEmployees.put(department.getName(), totalEmp);
                });
        return countEmployees;
    }



    @Override
    public Map<String, Long> countMaleEmployees() {
        Map<String, Long> countEmployees = new HashMap<>();

        departments.stream()
                .forEach(department -> {
                    long totalEmployeesMale = employees.stream()
                            .filter(employee -> employee.getDepartment().equals(department) && employee.getGender().equals(Gender.M))
                            .count();
                    countEmployees.put(department.getName(), totalEmployeesMale);
                });

        return countEmployees;
    }


    @Override
    public Map<String, Set<Employee>> countToMonthEmployees() {
        Map<String, Set<Employee>> dobToMonth = new HashMap<>();
        departments.stream()
                .forEach(department -> {
                    Set<Employee> employeeSet = employees.stream()
                            .filter(employee -> employee.getDoB().getMonthValue() == LocalDate.now().getMonthValue()
                                    && employee.getDepartment().equals(department))
                            .collect(Collectors.toSet());
                    dobToMonth.put(department.getName(), employeeSet);
                });
        return dobToMonth;
    }



}
