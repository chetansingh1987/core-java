package practical;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Conclusion
In short:

Always extract complex lambdas into named functions that you reference using ::. You should NEVER write -> {.
Avoid excessive call chaining � break them up using explanatory methods and variables, especially if the return type varies across these calls.
Whenever null annoys you, think about using the Optional. Twist your mind � you will have to apply functions to the magic box.
Realize when the variable thing is a function, and you work with that explicitly, pass a function to another function.
Loan Pattern means to have the function you give as the parameter that you use for a resource managed by the 'host' function. This leads to conceptually lighter, loosely coupled, and easy to test design.
Sometimes, you might want to have some arbitrary code to execute around another function. If that is the case, pass that code to the function as a parameter.
You can hook type-specific logic to your enums using method references to make sure each enum value is associated with a corresponding bit of logic.
 * @author chetan.singh1
 *
 */
public class StreamPractical2 {
	
	public void test() throws Exception {
		FileExporter exporter = new FileExporter() ;
		OrderWriter orderWriter = new OrderWriter();
		exporter.exportFile("testing",Uncheked.consumer(orderWriter::writeOrders));
	}
	
}

class FileExporter {
	
	public File exportFile(String fileName,Consumer<Writer> contentWriter) throws Exception {
		   
	       File file = new File("export/" + fileName);
	       try (Writer writer = new FileWriter(file)) {
	    	   contentWriter.accept(writer);
	           return file;
	       } catch (Exception e) {
	              throw e;
	       }
	}
}

class OrderWriter  {
	
	public void writeOrders(Writer writer) throws IOException {
		Repository repo = new Repository();
		writer.write("OrderID;Date\n");
		  Consumer<String> co = Uncheked.consumer(writer::write);//  co = writer.write(String)
		  repo.findByActiveTrue().stream().map(o -> o.getId() + ";" + o.getCreationDate()).forEach(co);
	}
}



class UserWriter {
	protected void writeUsers(Writer writer) throws IOException {
		Repository repo = new Repository();
		//Write USER CSV's
	}
}

@FunctionalInterface
interface ExcpetionInterface<T> {
   void  t(T x) throws Exception;
}
class Uncheked {
	public static <T> Consumer<T> consumer( ExcpetionInterface<T> exInterface) throws RuntimeException {
		return s->{ try {exInterface.t(s);}catch(Exception e) {}};
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
