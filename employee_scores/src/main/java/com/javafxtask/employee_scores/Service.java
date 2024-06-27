package com.javafxtask.employee_scores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {
    public static Result createResult(String path) {

        Result result = new Result();

        Map<Integer, List<ProjectPerEmployee>> projectsMap = csvLinesList(path);

        long durationDaysMax = 0;
        int employeeFirstId = 0;
        int employeeSecondId = 0;
        int projectId = 0;

        for (Map.Entry<Integer, List<ProjectPerEmployee>> entryProjectIdProjectsList : projectsMap.entrySet()) {

            List<ProjectPerEmployee> projectPerEmployees = entryProjectIdProjectsList.getValue();

            for (int i = 0; i < projectPerEmployees.size(); i++) {
                for (int j = i + 1; j < projectPerEmployees.size(); j++) {
                    ProjectPerEmployee currentProjectForEmployeePerEmployee = projectPerEmployees.get(i);
                    ProjectPerEmployee nextProjectForEmployeePerEmployee = projectPerEmployees.get(j);
                    LocalDate dateFrom = currentProjectForEmployeePerEmployee.getDateFrom().isAfter(nextProjectForEmployeePerEmployee.getDateFrom()) ?
                            currentProjectForEmployeePerEmployee.getDateFrom() : nextProjectForEmployeePerEmployee.getDateFrom();
                    LocalDate dateTo = currentProjectForEmployeePerEmployee.getDateTo().isBefore(nextProjectForEmployeePerEmployee.getDateTo()) ?
                            currentProjectForEmployeePerEmployee.getDateTo() : nextProjectForEmployeePerEmployee.getDateTo();

                    long currentDuration = ChronoUnit.DAYS.between(dateFrom, dateTo);
                    if (currentDuration > durationDaysMax) {
                        durationDaysMax = currentDuration;
                        employeeFirstId = currentProjectForEmployeePerEmployee.getEmployeeId();
                        employeeSecondId = nextProjectForEmployeePerEmployee.getEmployeeId();
                        projectId = entryProjectIdProjectsList.getKey();
                    }
                }
            }
        }

        result.setFirstEmployeeId(employeeFirstId);
        result.setSecondEmployeeId(employeeSecondId);
        result.setProjectId(projectId);
        result.setDaysWorked(durationDaysMax);

        return result;
    }

    private static Map<Integer, List<ProjectPerEmployee>> csvLinesList(String path) {
        Map<Integer, List<ProjectPerEmployee>> projectIdProjectDataMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(", ");
                int employeeId = Integer.parseInt(tokens[0]);
                int projectId = Integer.parseInt(tokens[1]);
                String startDateString = tokens[2];
                String endDateString = tokens[3];
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate startDate = null;
                LocalDate endDate = null;

                try {
                    startDate = LocalDate.parse(startDateString, formatter1);
                    endDate = endDateString.equals("NULL") ? LocalDate.now() : LocalDate.parse(endDateString, formatter1);
                } catch (DateTimeParseException e) {
                    try {
                        startDate = LocalDate.parse(startDateString, formatter2);
                        endDate = endDateString.equals("NULL") ? LocalDate.now() : LocalDate.parse(endDateString, formatter2);
                    } catch (DateTimeParseException ex) {
                        System.out.println("Error: The date string does not match any of the provided date formats.");
                    }
                }
                ProjectPerEmployee projectPerEmployee;

                if (startDate != null && endDate != null) {
                    projectPerEmployee = new ProjectPerEmployee(employeeId, startDate, endDate);
                    projectIdProjectDataMap.putIfAbsent(projectId, new ArrayList<>());
                    projectIdProjectDataMap.get(projectId).add(projectPerEmployee);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projectIdProjectDataMap;
    }
}
