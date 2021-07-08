package java8.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoldmanSachs {

	
	public static void main(String[] args) {
		Map<String,List<Integer>> data = new HashMap<>();
		data.put("A", Arrays.asList(10,20,30));
		data.put("B", Arrays.asList(10,20,30));
		data.put("C", Arrays.asList(10,20,30));
		
		//data----->    <name,totalMarks>  ------>max
		
		Map<String,Integer> temp = data.entrySet().stream().collect(HashMap::new , GoldmanSachs::accumulate , GoldmanSachs::combine );
		Map<String,Integer> studentMaxMarks = temp.entrySet().stream().collect( HashMap::new , GoldmanSachs::accumulateMax , GoldmanSachs::combineMax  );
		
		studentMaxMarks.forEach((k,v)->System.out.println("Name: "+k+" Total Marks:"+v));
	}
	
	
	public static void accumulate(Map<String,Integer> m , Map.Entry<String,List<Integer>> input) {
		m.put(input.getKey(), input.getValue().stream().mapToInt(x->x).sum());
	}
	
	public static Map<String,Integer> combine(Map<String,Integer> m1 , Map<String,Integer> m2) {
		m1.putAll(m2);
		return m1;
	}
	
	public static void accumulateMax(Map<String,Integer> m , Map.Entry<String,Integer> input) {
		if(m.isEmpty()) m.put(input.getKey(), input.getValue());
		int pMax = (int) m.values().toArray()[0];
		if(pMax<input.getValue()) {
			m.clear();
			m.put(input.getKey(),input.getValue());
		}else if(pMax==input.getValue()) {
			m.put(input.getKey(),input.getValue());
		}
	}
	
	public static Map<String,Integer> combineMax(Map<String,Integer> m1 , Map<String,Integer> m2) {
		int p1Max = (int) m1.values().toArray()[0];
		int p2Max = (int) m2.values().toArray()[0];
		if(p1Max==p2Max) m1.putAll(m2);
		if(p1Max<p2Max) {
			m1.clear();
			m1.putAll(m2);
		}
		return m1;
	}
}
