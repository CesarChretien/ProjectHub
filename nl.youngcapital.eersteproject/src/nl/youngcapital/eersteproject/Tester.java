package nl.youngcapital.eersteproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tester {

	public static void main(String args[]) throws FileNotFoundException {
		
		FileInputStream fis = new FileInputStream("hi.txt");
		
		boolean a = true;
		boolean b = false;
		if((b = a) != a);
		System.out.println(a + " " + b);
		byte by = 1;
		int i  = 1;
		short s = 1;
		long l = 1;
		double d = 1;
		float f = 1;
		char c = 'z';
		
		boolean kek = ((Integer)(int)s).equals((Integer)i);
		
		boolean check = f == d;
		System.out.println(kek);
	}
	
	class A {}
	class B extends A {
		//B(){}
		B(String s){}
	}
	class C extends B {
		static final String s = "";
		C(int i){ super(s); }
	}
}
