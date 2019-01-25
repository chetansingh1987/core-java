package core.java8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class FilehandlingTest {


	public static void main(String[] args) {
		writeData("Hello All", "chetan.txt");
		readData("chetan.txt");
	}


	public static void readData(String path) {
		Path filePath = FileSystems.getDefault().getPath(path);
		try(BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
			String line = null;
			while((line=bufferedReader.readLine())!=null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void writeData(String data , String path) {
		Path filePath = FileSystems.getDefault().getPath(path);
		try(BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);)
		{
			bufferedWriter.write(data);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


}
