package Entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private Department department;
    Gender gender;
    private LocalDate DoB;

    public Employee(int id, String name, Department department, Gender gender, LocalDate doB) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        DoB = doB;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate doB) {
        DoB = doB;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", DoB=" + DoB +
                '}';
    }
}
