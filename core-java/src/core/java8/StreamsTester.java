package core.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;


enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
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

public class StreamsTester {
	
	
	@Test
	public void test1() {
		List<BlogPost> posts = getSample();
		Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream().collect(Collectors.groupingBy(BlogPost::getType));
		postsPerType.forEach((k,v)->{
			System.out.println(k);
			v.stream().forEach(b->System.out.println(b.getTitle()));
		}
		);
	}
	
	

	
	public void test2() {
		Student s1 = new Student("Anurag");
		Student s2 = new Student("Anupam");
		Student s3 = new Student("Chetan");
		
		List<Student> listStu = Arrays.asList(s1,s2,s3);
		listStu.stream().map(student->student.getName()).filter(name->name.startsWith("A")).collect(Collectors.toList()).forEach(System.out::println);
	}
	
	public void test3() {
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		Optional<Integer> intOptional = numbers.reduce((i,j) -> {return i*j;});
		if(intOptional.isPresent()) System.out.println("Multiplication = "+intOptional.get()); //120
	}
	
	public void test4() {
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		System.out.println(numbers.count());
	}
	
	public void test5() {
		Stream<Integer> numbers3 = Stream.of(1,2,3,4,5);
		System.out.println(numbers3.anyMatch(i->i==4));
		
		Stream<Integer> numbers4 = Stream.of(1,2,3,4,5);
		System.out.println(numbers4.noneMatch(i->i==4));
		
		Stream<Integer> numbers5 = Stream.of(1,2,3,4,5);
		System.out.println(numbers5.allMatch(i->i<10));
	}
	
	
	public List<BlogPost> getSample() {
		BlogPost b1 = new BlogPost("Title A","Author A",BlogPostType.REVIEW,1);
		BlogPost b2 = new BlogPost("Title D","Author D",BlogPostType.REVIEW,1);
		BlogPost b3 = new BlogPost("Title B","Author B",BlogPostType.NEWS,1);
		BlogPost b4 = new BlogPost("Title F","Author B",BlogPostType.NEWS,1);
		BlogPost b5 = new BlogPost("Title C","Author C",BlogPostType.NEWS,1);
		BlogPost b6 = new BlogPost("Title E","Author E",BlogPostType.GUIDE,1);
		return Arrays.asList(b1,b2,b3,b4,b5,b6);		
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
}

