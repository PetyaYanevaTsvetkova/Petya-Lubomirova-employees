package com.javafxtask.employee_scores;

import java.util.Scanner;

public class ConsoleSolution {
    private static final String PATH = "src/main/resources/input.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String path = scanner.nextLine();

        Result result = Service.createResult(path);
        System.out.println(result);

    }
}


