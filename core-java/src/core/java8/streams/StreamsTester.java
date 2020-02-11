package core.java8;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;


public class StreamsTester {


	@Test //Flat Map test
	public void test23() {
		List<String> names1 = Arrays.asList("Dzmitry", "John");
		List<String> names2 = Arrays.asList("David", "Laura");
		Stream<List<String>> s = Stream.of(names1, names2);
		s.flatMap(names -> names.stream()).forEach(System.out::println);
	}
	@Test
	public void test22() {
		IntStream.of(10).forEach(x->System.out.println(x));
	}
	
	@Test
	public void test1() {
		Student s1 = new Student("Anurag");
		Student s2 = new Student("Anupam");
		Student s3 = new Student("Chetan");
		List<Student> listStu = Arrays.asList(s1,s2,s3);
		System.out.println(listStu.parallelStream().findAny());
	}

	@Test
	public void test2() {
		Student s1 = new Student("Anurag");
		Student s2 = new Student("Anupam");
		Student s3 = new Student("Chetan");
		
		List<Student> listStu = Arrays.asList(s1,s2,s3);
		listStu.stream().map(student->student.getName()).filter(name->name.startsWith("A")).collect(Collectors.toList()).forEach(System.out::println);
	}
	
	@Test
	public void test3() {
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		Optional<Integer> intOptional = numbers.reduce((i,j) -> {
			System.out.println(i+" "+j);
			return i*j;});
		if(intOptional.isPresent()) System.out.println("Multiplication = "+intOptional.get()); //120
	}
	
	@Test
	public void test4() {
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		assertTrue(numbers.count()==5);
	}
	
	@Test
	public void test5() {
		Stream<Integer> numbers3 = Stream.of(1,2,3,4,5);
		System.out.println(numbers3.anyMatch(i->i==4));
		
		Stream<Integer> numbers4 = Stream.of(1,2,3,4,5);
		System.out.println(numbers4.noneMatch(i->i==4));
		
		Stream<Integer> numbers5 = Stream.of(1,2,3,4,5);
		System.out.println(numbers5.allMatch(i->i<10));
	}
	
	@Test
	public void test6() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream().collect(Collectors.groupingBy(p->p.getType()));
		System.out.println(Arrays.toString(postsPerType.entrySet().toArray()));
	}
	
	
	@Test
	//Lets Create a Map with Tuple as Key and BlogPost as value
	public void test7() {
		List<BlogPost> posts = getSample();
		Map<Tuple,List<BlogPost>> map = posts.stream().collect(Collectors.groupingBy(post->new Tuple(post.getType(),post.getAuthor())));
		System.out.println(Arrays.toString(map.entrySet().toArray()));
	}
	
	@Test
	public void test8() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, Set<BlogPost>> postsPerType = posts.stream().collect(groupingBy(p->p.getType(),toSet()));
		System.out.println(Arrays.toString(postsPerType.entrySet().toArray()));
	}
	
	
	
	@Test
	//Providing a Secondary Group By Collector
	public void test9() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, Map< String ,List<BlogPost> > > map = posts.stream().collect(groupingBy(BlogPost::getType, 
																			    	   groupingBy(BlogPost::getAuthor)));
		map.forEach((k,v)->{
			System.out.println(k);
			System.out.println(Arrays.toString(v.entrySet().toArray()));
		});
	}
	
	//Getting the Average from Grouped Results
	@Test
	public void test10() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, Double >  map = posts.stream().collect(groupingBy(BlogPost::getType,  averagingInt(BlogPost::getLikes)));
		System.out.println(Arrays.toString(map.entrySet().toArray()));
	}
	
	//Getting the Sum from Grouped Results
	@Test
	public void test11() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, Integer >  map = posts.stream().collect(groupingBy(BlogPost::getType,  summingInt(BlogPost::getLikes)));
		System.out.println(Arrays.toString(map.entrySet().toArray()));
	}
	
	//Getting max Likes
	@Test
	public void test12() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, Optional<BlogPost> >  map = posts.stream().collect(groupingBy(BlogPost::getType,  maxBy(Comparator.comparingInt(BlogPost::getLikes) )));
		System.out.println(Arrays.toString(map.entrySet().toArray()));
	}
	
	//Getting the Sum from Grouped Results
	@Test
	public void test13() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, IntSummaryStatistics >  map = posts.stream().collect(groupingBy(BlogPost::getType,  summarizingInt(BlogPost::getLikes)));
		System.out.println(Arrays.toString(map.entrySet().toArray()));
	}
	
	//Getting the Sum from Grouped Results
	@Test
	public void test15() {
		List<Integer> listNum = Arrays.asList(1,2,3);
		IntSummaryStatistics intSummary = listNum.stream().mapToInt(x->x).summaryStatistics();
		intSummary.getAverage();
	}
	
	public List<BlogPost> getSample() {
		BlogPost b1 = new BlogPost("Title A","Author A",BlogPostType.REVIEW,3);//avg like 4
		BlogPost b2 = new BlogPost("Title D","Author D",BlogPostType.REVIEW,5);
		BlogPost b3 = new BlogPost("Title B","Author B",BlogPostType.NEWS,6);//avg like 4
		BlogPost b4 = new BlogPost("Title F","Author B",BlogPostType.NEWS,3);
		BlogPost b5 = new BlogPost("Title C","Author C",BlogPostType.NEWS,3);
		BlogPost b6 = new BlogPost("Title E","Author E",BlogPostType.GUIDE,5);//avg like 5
		return Arrays.asList(b1,b2,b3,b4,b5,b6);		
	}
	
	
	/**
	 * Stream optimiuzation is done so we dont iterate 100 numbers but only 
	 * five because of intermdediate operationlimit
	 */
	
	@Test
	public void test14() {
		IntStream.range(0, 100).peek(i->System.out.println("Peeked "+i)).limit(5).forEach(System.out::print);
	}

}

enum  BlogPostType {NEWS,REVIEW,GUIDE}

class Tuple { 
	BlogPostType type; 
	String author;
	public Tuple(BlogPostType type, String author) {
		super();
		this.type = type;
		this.author = author;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tuple [type=" + type + ", author=" + author + "]";
	}
	
	
}
class BlogPost {
    String title;
    String author;
    BlogPostType type;
    int likes;
	public BlogPost(String title, String author, BlogPostType type, int likes) {
		super();
		this.title = title;
		this.author = author;
		this.type = type;
		this.likes = likes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BlogPostType getType() {
		return type;
	}
	public void setType(BlogPostType type) {
		this.type = type;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + likes;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (likes != other.likes)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BlogPost [title=" + title + "]";
	}
	
	
}


class Student {
	String name ;
	public Student(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}
}

