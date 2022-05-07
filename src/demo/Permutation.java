package demo;

import java.util.HashSet;

// Edward(Yican) Yin
class Permutation {

	public static void main (String[] args) {
		// With repeating chars
		String s = "1 2 3 4 2 6";
		System.out.println("========= [1 2 3 4 2 6] =================");
		String[] tokens = s.split("\\s");
		Permutation permutations = new Permutation();
		permutations.printPermutations (tokens, 0);
		
		// without repeating chars
		s = "1 2 3 4 5 6";
		System.out.println("========= [1 2 3 4 5 6] =================");
		tokens = s.split("\\s");;
		permutations.printPermutations (tokens, 0);
		

		// Solution 2
		System.out.println("========= [1 2 3 4 5 6] =================");
		permutations.printPermutations2 (tokens, 0, tokens.length-1);

	}
	
	private void printPermutations(String[] tokens,int curIndex) {
		if (curIndex == tokens.length-1) {
			System.out.println(String.join(" ", tokens));
			return;
		}
		HashSet<String> set = new HashSet<>();
		for (int i = curIndex; i < tokens.length; i++) {
			if (set.contains(tokens[i])) continue;
			set.add(tokens[i]);
			
			swap (tokens, i, curIndex);
			printPermutations(tokens, curIndex + 1);
			swap (tokens, i, curIndex);
		}
	}
	
	private void printPermutations2(String[] tokens, int left, int right) {
		if (left == right) {
			System.out.println(String.join(" ", tokens));
		} else {
			for (int i = left; i <= right; i++) {			
				String[] swapped = this.swap(tokens, left, i);
				this.printPermutations2(swapped, left+1, right);
			}
		}
	}
	
	private String[] swap(String[] tokens, int i, int j) {
		String temp = tokens[i];
		tokens[i] = tokens[j];
		tokens[j] = temp;
		return tokens;
	}


}
