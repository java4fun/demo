package demo;

import java.util.Stack;

// Edward(Yican) Yin
public class ListWeightedSum {

	// Input: [[1,1],2,[1,1]]
	// Output: 10 
	// Input: [1,[4,[6]]]
	// Output: 27 
	public static void main (String[] args) {
		
		String s = "[[1,1],2,[1,1]]";
		
		System.out.println("wighted sum of [[1,1],2,[1,1]]: " + sum(s));
		
		s = "[1,[4,[6]]]";
		System.out.println("wighted sum of [1,[4,[6]]]: " + sum(s));
	}
	
	static int sum (String s) {
	
		Stack<Integer> depthStack = new Stack<>();
		int sum = 0;
		int depth = 0;
		String normalized = s.replaceAll("\\s", "");
		
		int numStart = -1;
		int numEnd = -1;

		for(int i = 0; i < normalized.length(); i++) {

			char c = normalized.charAt(i);

			if (c == '[') {
				depth++;
				depthStack.push(depth);
			} else if (c == ']') {
				if (numStart != -1) {
					numEnd = i;
					sum += depth * Integer.valueOf(normalized.substring(numStart, numEnd));
					numStart = -1; numEnd = -1;
				}
				depthStack.pop();
				depth--;
			} else if (c == ',') {
				if (numStart != -1) {
					numEnd = i;
					
					sum += depth * Integer.valueOf(normalized.substring(numStart, numEnd));
					numStart = -1; numEnd = -1;
				}
			} else if (Character.isDigit(c)) {
				if (numStart == -1) numStart = i;
			}

			
		}
		return sum;
		
	}
	
	
	
}
