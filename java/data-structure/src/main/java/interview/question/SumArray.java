package interview.question;

public class SumArray {
	
	
	public static void main(String[] args) {
		int[] i = {10,20,30};
		Integer[] sumArray = new Integer[i.length];
		getSumArray(0, 0, i, sumArray);
		for(int j : sumArray) System.out.println(j);
	}
	
	public static int getSumArray(int prevSum , int curIndex , int[] input ,Integer[] sumArray) {
		if(curIndex>=input.length) return 0;
		int res =  getSumArray(prevSum+input[curIndex], 1+curIndex, input, sumArray);
		int sum = prevSum + res;
		sumArray[curIndex] = sum;
		return input[curIndex]+res;
	}

}
