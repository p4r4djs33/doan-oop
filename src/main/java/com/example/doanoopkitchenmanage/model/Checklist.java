package com.example.doanoopkitchenmanage.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "checklist")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dateCreated;
    private String data;
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;


    public Checklist() {
    }

    public Checklist(Long id, String dateCreated, String data, String status, Employee employee, List<Ingredient> ingredients) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.data = data;
        this.status = status;
        this.employee = employee;
        this.ingredients = ingredients;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
