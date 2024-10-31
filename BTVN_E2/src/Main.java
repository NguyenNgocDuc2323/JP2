import Entity.Department;
import Entity.Employee;
import Entity.Gender;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<Department>();
        List<Employee> employees = new ArrayList<>();
        Map<String,Long> countEmployees = new HashMap<>();
        Map<String,Set<Employee>> dobToMonth = new HashMap<>();
        departments.add(new Department(1,"HR","Human Resource"));
        departments.add(new Department(2,"IT","Information Technology"));

        employees.add(new Employee(1,"Nguyen Ngoc Duc",departments.get(0), Gender.F,LocalDate.of(2001,10,23)));
        employees.add(new Employee(2,"Le Thi Ha",departments.get(1),Gender.F,LocalDate.of(2001,3,13)));
        employees.add(new Employee(3,"Nguyen Ngoc Duc",departments.get(1),Gender.M,LocalDate.of(1991,4,25)));


//        //count total employees for each department name
//        departments.stream()
//                .forEach(department -> {
//                    long totalEmp = employees.stream()
//                            .filter(employee -> employee.getDepartment().getId() == department.getId())
//                            .collect(Collectors.toSet())
//                            .stream().count();
//                    countEmployees.put(department.getCode(), totalEmp);
//                });
//        System.out.println(countEmployees);
//
//        // using getOrDefault
//        departments.forEach(department -> {
//            employees.stream()
//                    .filter(employee -> employee.getDepartment().getId() == department.getId())
//                    .peek(employee -> {
//                         countEmployees.put(department.getCode(),countEmployees.getOrDefault(department.getCode(),0L) + 1);
//                    });
//        });
//        System.out.println(countEmployees);
//
//        // using Set<Obj> and size() convert dataType to Long
//        departments.stream()
//                .forEach(department -> {
//                    Set<Employee> employeeSet = employees.stream()
//                            .filter(employee -> employee.getDepartment().getId() == department.getId())
//                            .collect(Collectors.toSet()); // phải dùng toSet trong Collectors
//                    countEmployees.put(department.getCode(),(long)employeeSet.size());
//                });
//        System.out.println(countEmployees);

        departments.stream()
                .forEach(department -> {
                    long totalEmployeesMale = employees.stream()
                            .filter(employee -> employee.getDepartment().equals(department) && employee.getGender().equals(Gender.M))
                            .count();
                    countEmployees.put(department.getCode(), totalEmployeesMale);
                });

        System.out.println(countEmployees);


        departments.stream()
                .forEach(department -> {
                    Set<Employee> employeeSet = employees.stream()
                            .filter(employee -> employee.getDoB().getMonthValue() == LocalDate.now().getMonthValue()
                                    && employee.getDepartment().equals(department))
                            .collect(Collectors.toSet());
                    dobToMonth.put(department.getCode(), employeeSet);
                });

        System.out.println(dobToMonth);

    }
}