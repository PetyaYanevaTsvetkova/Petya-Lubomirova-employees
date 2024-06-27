package com.javafxtask.employee_scores;

public class Result {
    private int firstEmployeeId;
    private int secondEmployeeId;
    private int projectId;
    private long daysWorked;

    public int getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public void setFirstEmployeeId(int firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public int getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public void setSecondEmployeeId(int secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(long daysWorked) {
        this.daysWorked = daysWorked;
    }

    @Override
    public String toString() {
        return "Result{" +
                "firstEmployeeId=" + firstEmployeeId +
                ", secondEmployeeId=" + secondEmployeeId +
                ", projectId=" + projectId +
                ", daysWorked=" + daysWorked +
                '}';
    }
}
