package me.ohwhite.crackme1;

import java.util.ArrayList;
import java.util.Scanner;

public class crackme1 {
  static ArrayList<Integer> arr_int = new ArrayList<>();
  
  static ArrayList<String> arr_string = new ArrayList<>();
  
  public static void main(String[] args) {
    func1();
    func2();
    System.out.println(arr_string.get(0));
    Scanner scanner = new Scanner(System.in);
    try {
      int input = scanner.nextInt();
      if (input != ((Integer)arr_int.get(0)).intValue())
        return; 
    } catch (Exception e) {
      System.exit(-7);
    } 
    System.out.println(arr_string.get(1));
  }
  
  public static void func2() {
    arr_int.add(Integer.valueOf(5256));
  }
  
  public static void func1() {
    arr_string.add("Enter an 8 digit code: ");
    arr_string.add("You have successfully logged in!");
  }
}