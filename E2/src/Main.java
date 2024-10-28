import Controller.EmployeeController;
import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<Department>();
        List<Employee> employees = new ArrayList<Employee>();
        EmployeeService employeeService = new EmployeeService(employees);
        EmployeeController employeeController = new EmployeeController(employeeService);
        departments.add(new Department(1,"Sale"));
        departments.add(new Department(2,"DEV"));
        departments.add(new Department(3,"IT"));
        employees.add(new Employee(1,"Nguyen Ngoc Duc",departments.get(0), LocalDate.of(2001,3,23), Gender.M,"Thanh Hoa","Thanh Hoa","0886189630"));
        employees.add(new Employee(2,"Nguyen Dang Dung",departments.get(1), LocalDate.of(1998,5,2), Gender.M,"Ha Noi","Ha Noi","09632433454"));
        employees.add(new Employee(1,"Le Thi Hong",departments.get(2), LocalDate.of(1995,7,12), Gender.F,"Ho Chi Minh","Ho Chi Minh","096324331322"));
        Map<String, List<Employee>> foundEmployees = employeeController.searchEmployee("DEV");
        foundEmployees.forEach((departmentName, employeeList) -> {
            System.out.println("Department: " + departmentName);
            employeeList.forEach(employee -> System.out.println("Employee: " + employee));
        });
        Map<String,List<Employee>> groupByDepartmentNames = employeeController.groupByDepartmentName();
        groupByDepartmentNames.forEach((departmentName, employeeList) -> {
            System.out.println("Department: " + departmentName);
            employeeList.forEach(employee -> System.out.println("Employee: " + employee));
        });
        Map<String,Long> coundEmployeesByDepartmentName = employeeController.countTotalEmployeesByDepartment();
        coundEmployeesByDepartmentName.forEach((departmentName, numberEmployees) -> {
            System.out.println("Department " + departmentName + " has " + numberEmployees + " employees");
        });
    }
}