package interview.question;

import java.util.List;

public class CountDuplicates {

public void test1() {
	
}

public static int countDuplicates(List<Integer> numbers)
{
	int index=numbers.size();
	int duplicates[]=new int[index];
	int count=0;
	for(int i=0;i<index;i++)
	{
		duplicates[numbers.get(i)]=duplicates[numbers.get(i)]+1;
	}
	for(int i=0;i<index;i++)
	{
		if(duplicates[i]>1)
			count++;
	}
	return count;
}

}