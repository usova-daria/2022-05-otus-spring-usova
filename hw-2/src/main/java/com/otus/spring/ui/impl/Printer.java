package com.otus.spring.ui.impl;

public class Printer {

    public final static String NEW_LINE = System.lineSeparator();

    public void print(String output) {
        System.out.print(output);
    }

    public void println() {
        println(NEW_LINE);
    }

    public void println(String output) {
        print(output + NEW_LINE);
    }

    public void breakLine() {
        print(NEW_LINE);
    }

}
