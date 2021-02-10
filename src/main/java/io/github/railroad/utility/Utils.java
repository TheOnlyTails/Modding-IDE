package io.github.railroad.utility;

public interface Utils {
    String OS = System.getProperty("os.name").toUpperCase();

    static boolean windows() { return OS.contains("WIN"); }
    static boolean mac() { return OS.contains("MAC"); }
    static boolean unix() { return OS.contains("NIX") || OS.contains("NUX") || OS.contains("AIX"); }
}
