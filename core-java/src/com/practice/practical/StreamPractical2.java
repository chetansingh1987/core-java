package com.practice.practical;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamPractical2 {
	
	
	public File exportFile(String fileName) throws Exception {
		   Repository repo = new Repository();
	       File file = new File("export/" + fileName);
	       try (Writer writer = new FileWriter(file)) {
	              writer.write("OrderID;Date\n");
	              repo.findByActiveTrue().stream().map(o -> o.getId() + ";" + o.getCreationDate()).forEach(writer::write);
	              return file;
	       } catch (Exception e) {
	              throw e;
	       }
	}
}
class Repository {
	public List<Order> getOrderList(){
		Order order1=null;
		Order order2=null;
		SimpleDateFormat sf = new SimpleDateFormat("DD//MM/YYYY");
		try {
			 order1 = new  Order(1, sf.parse("10/12/2018"),true);
			 order2 = new  Order(2, sf.parse("12/12/2018"),false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Arrays.asList(order1,order2);
		
	}
	
	public List<Order> findByActiveTrue() {
		Predicate<Order> isActive = Order::isActive;
		return getOrderList().stream().filter(isActive).collect(Collectors.toList());
	}
}
class Order {
	int id;
	Date creationDate;
	boolean active;
	
	public Order(int id, Date creationDate, boolean active) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public boolean isActive() {
		return active;
	}

	
}
