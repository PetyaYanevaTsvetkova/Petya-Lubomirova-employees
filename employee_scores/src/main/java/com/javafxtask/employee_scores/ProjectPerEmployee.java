package com.javafxtask.employee_scores;

import java.time.LocalDate;

public class ProjectPerEmployee {
    private int employeeId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public ProjectPerEmployee(int employeeId, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeId = employeeId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
