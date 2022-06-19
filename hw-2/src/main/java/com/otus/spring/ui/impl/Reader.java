package com.otus.spring.ui.impl;

import java.util.Scanner;

public class Reader {

    private final Scanner SCANNER = new Scanner(System.in);

    public String read() {
        return SCANNER.nextLine();
    }

}
