package com.example.doanoopkitchenmanage.model;

import javax.persistence.*;

@Entity
@Table(name = "main_ingredient")
public class MainIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String amount;
    private String dateImport;
    private String dish;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public MainIngredient() {
    }

    public MainIngredient(Long id, String name, String amount, String dateImport, String dish, Employee employee) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.dateImport = dateImport;
        this.dish = dish;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateImport() {
        return dateImport;
    }

    public void setDateImport(String dateImport) {
        this.dateImport = dateImport;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "MainIngredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", dateImport='" + dateImport + '\'' +
                ", dish='" + dish + '\'' +
                ", employee=" + employee +
                '}';
    }
}
