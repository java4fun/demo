package demo;

import java.io.PrintWriter;
import java.io.StringWriter;

class Demo1 {

	public static void main (String[] args) {
		String s = "ABC";
		int n = s.length();
		Demo1 permutations = new Demo1();
		permutations.calculate (s, 0, n-1);
		
		Exception ex = new Exception("Test");
		System.out.println(ex.getMessage());
		System.out.println();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
		System.out.println(sw.toString());
	}
	
	
	private void calculate(String str, int left, int right) {
		if (left == right) {
			System.out.println(str);
		} else {
			for (int i = left; i <= right; i++) {
				String swapped = this.swap(str, left, i);
				this.calculate(swapped, left+1, right);
			}
		}
	}
	
	private String swap(String str, int i, int j) {
		char temp;
		char [] chars = str.toCharArray();
		temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
		return String.valueOf(chars);
	}

}
