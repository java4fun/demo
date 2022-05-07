package demo;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightedSum {

	// Input: [[1,1],2,[1,1]]
	// Output: 10 
	// Input: [1,[4,[6]]]
	// Output: 27 
	public static void main (String[] args) {
		List<NestedInteger> list = new ArrayList<>();
		
		
	}
	
	
	public int depthWeightedSum(List<NestedInteger> nestedList) {
	    return helper(nestedList, 1);
	}
	 
	public int helper(List<NestedInteger> nestedList, int depth){
	    if(nestedList==null||nestedList.size()==0)
	        return 0;
	 
	    int sum=0;
	    for(NestedInteger ni: nestedList){
	        if(ni.isInteger()){
	            sum += ni.getInteger() * depth;
	        }else{
	            sum += helper(ni.getList(), depth+1);
	        }
	    }
	 
	    return sum;
	}
	
}
