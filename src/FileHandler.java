// ds imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// file i/o imports
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FileHandler {


	// reads file at the given path and returns hashmap that contains String: wn/mn, List<String>:  m1/w1 ... mn/wn. 
	public static HashMap<String, List<String>> readFileToHashMap(String filePath) {
		
		HashMap<String, List<String>> preferences = new HashMap<String, List<String>>();
		
		String line = "";
		
		try {
			// reader
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// read lines one by one until the last line
			while((line = bufferedReader.readLine()) != null) {
				
				// split current line by tab character
				String row[] = line.split("\\t");

				// generate list of rows from split strings
				List<String> rowList = new ArrayList<String>(Arrays.asList(row));
				
				// get first string for subject
				String firstString = rowList.remove(0);

				// add imported elements to a hashmap
				preferences.put(firstString, rowList);
			}
			
			bufferedReader.close();
			
			return preferences;
			
		} catch(Exception e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
		
		return null;
	}
	
	public static void writeHashMapToFile(HashMap<String,String> output) {
		
		File file = new File("output.txt");
		
		// create file if not exists
		if(!file.exists()) {
			try {
				file.createNewFile();
			
			} catch (IOException e) {
				System.err.println("Error writing file: " + e.getMessage());
			}
		}
		
		FileWriter writer;
		
		try {
			writer = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			// write to output.txt line by line, formatted: mn wn
			for(Object obj : output.keySet()) {
				bufferedWriter.write(output.get(obj) + "\t" + obj);
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
			
		} catch (IOException e) {
			System.err.println("Error writing file: " + e.getMessage());
		}
	}
	
	public static void printHashMap(HashMap<String, List<String>> map) {
		for(Object obj : map.keySet()) {
			System.out.println(obj);
			System.out.println(map.get(obj));
		}
	}
	
	// sample data generator I/O
	public static void writeSamplesToFile(String gender, int sampleSize, HashMap<String, List<String>> output) {
		
		// for example, m100.txt
		File file = new File(gender + sampleSize + ".txt");
		
		// create file if not exists
		if(!file.exists()) {
			try {
				file.createNewFile();
			
			} catch (IOException e) {
				System.err.println("Error writing file: " + e.getMessage());
			}
		}
		
		FileWriter writer;
		
		try {
			writer = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			// formatting w1	m1	m2 ... mn
			for(Object obj : output.keySet()) {
				bufferedWriter.write(obj + "\t");
				
				for(Object subObj : output.get(obj)) {
					bufferedWriter.write(subObj + "\t");
				}
				
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
			
		} catch (IOException e) {
			System.err.println("Error writing file: " + e.getMessage());
		}
	}
}
