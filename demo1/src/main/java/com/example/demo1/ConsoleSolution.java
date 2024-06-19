package com.example.demo1;

import java.util.Scanner;

public class ConsoleSolution {
    private static final String PATH = "D:/JAVA/SirmaTask/input.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String path = scanner.nextLine();

        Result result = Service.createResult(path);
        System.out.println(result);

    }
}


