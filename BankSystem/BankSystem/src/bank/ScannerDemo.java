package bank;

import java.util.Scanner;

public class ScannerDemo {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			
//			sc.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
