package core.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

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

enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
}