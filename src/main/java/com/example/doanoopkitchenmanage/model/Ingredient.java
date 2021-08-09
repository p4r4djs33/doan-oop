package com.example.doanoopkitchenmanage.model;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_for_day")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String amountBegin;
    private String amountEnd;
    private String date;
    @ManyToOne
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, String amountBegin, String amountEnd, String date, Checklist checklist, Report report) {
        this.id = id;
        this.name = name;
        this.amountBegin = amountBegin;
        this.amountEnd = amountEnd;
        this.date = date;
        this.checklist = checklist;
        this.report = report;
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

    public String getAmountBegin() {
        return amountBegin;
    }

    public void setAmountBegin(String amountBegin) {
        this.amountBegin = amountBegin;
    }

    public String getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(String amountEnd) {
        this.amountEnd = amountEnd;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
