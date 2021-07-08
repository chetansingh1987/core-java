package com.cs.compression;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;

public class TestCompression {
	public static String compress(String myValueAsXml) {
		if (myValueAsXml != null) {
			ByteArrayOutputStream byteArrStream = new ByteArrayOutputStream();
			GZIPOutputStream gzip = null;
			try {
				gzip = new GZIPOutputStream(byteArrStream);
				gzip.write(myValueAsXml.getBytes());

			} catch (IOException e) {
			} finally {
				try {
					if (gzip != null)
						gzip.close();
				} catch (IOException e) {
				}
			}
			
			return Base64.encodeBase64String(byteArrStream.toByteArray());
		}
		return null;
	}

	public static String decompress(String ip) {
		byte[] valueAsByte=Base64.decodeBase64(ip);
		StringBuilder outStr = null;
		GZIPInputStream gis = null;
		if (valueAsByte != null) {
			outStr = new StringBuilder();
			try {
				gis = new GZIPInputStream(new ByteArrayInputStream(valueAsByte));
				outStr.append(org.apache.commons.io.IOUtils.toString(gis));
				/*outStr.append(BufferedReader bf = new BufferedReader(new InputStreamReader(gis));

				String line;
				while ((line = bf.readLine()) != null) {
					outStr.append(line);
					outStr.append('\n');
				}*/

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (gis != null)
					try {
						gis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return outStr.toString();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String com = "";
		com="";
		String decom = decompress(com);
		System.out.println(com.substring(0, 50));
		System.out.println(decompress(decom).substring(0, 50));
	}
	
	public String getData(String fileName) {
		String content = null;
		try {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		content=stringBuilder.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	//Since JDK 7, NIO
	private static void writeBytesToFileNio(byte[] bFile, String fileDest) {

	    try {
	        Path path = Paths.get(fileDest);
	        Files.write(path, bFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	/*public static void main(String[] args) {
		String data = vc.getData("C:/CS_Ericsson/Office/Assigned-Wrok/FESimulation/Original_instance.txt");
		byte[] b = vc.compress(data);
		vc.writeBytesToFileNio(b, "C:/CS_Ericsson/Office/Assigned-Wrok/FESimulation/Compressed_Instance.txt");
		//System.out.println(vc.decompress(b));
	}*/
}
