import Controller.EmployeeController;
import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.EmployeeService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<Department>();
        List<Employee> employees = new ArrayList<Employee>();
        HashSet<Employee> countEmployees = new HashSet<>();
        EmployeeService employeeService = new EmployeeService(employees,departments);
        EmployeeController employeeController = new EmployeeController(employeeService);
        departments.add(new Department(1,"Sale"));
        departments.add(new Department(2,"DEV"));
        departments.add(new Department(3,"IT"));
        employees.add(new Employee(1,"Nguyen Ngoc Duc",departments.get(0), LocalDate.of(2001,3,23), Gender.M,"Thanh Hoa","Thanh Hoa","0886189630"));
        employees.add(new Employee(2,"Nguyen Dang Dung",departments.get(1), LocalDate.of(1998,10,2), Gender.M,"Ha Noi","Ha Noi","09632433454"));
        employees.add(new Employee(1,"Le Thi Hong",departments.get(2), LocalDate.of(1995,7,12), Gender.F,"Ho Chi Minh","Ho Chi Minh","096324331322"));

        String departmentNameToSearch = "HR";

        Map<String, List<Employee>> foundEmployees = employeeController.searchEmployee(departmentNameToSearch);
        System.out.println("Employees in department " + departmentNameToSearch + ": " + foundEmployees);

        Map<String, List<Employee>> groupedEmployees = employeeService.groupByDepartmentName();
        System.out.println("Grouped Employees by Department: " + groupedEmployees);

        Map<String, Long> totalEmployeesCount = employeeService.countTotalEmployeesByDepartment();
        System.out.println("Total Employees by Department: " + totalEmployeesCount);

        Map<String, Long> maleEmployeesCount = employeeService.countMaleEmployees();
        System.out.println("Male Employees by Department: " + maleEmployeesCount);

        Map<String, Set<Employee>> monthlyEmployees = employeeService.countToMonthEmployees();
        System.out.println("Employees with birthdays this month: " + monthlyEmployees);
    }
}