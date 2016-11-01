// ds imports
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SampleGenerator {

	public static void generateSampleData(String gender, int sampleSize) {
		HashMap<String, List<String>> generatedData = new HashMap<String, List<String>>();
        
		String preference = "";
		if(gender == "m") preference = "w";
		else preference = "m";
		
        for(int i = 1; i <= sampleSize; i++) 
        {
        	generatedData.put(gender + i, generateRandomList(preference, sampleSize));
        }
        
        FileHandler.writeSamplesToFile(gender, sampleSize, generatedData);
	}

	private static ArrayList<String> generateRandomList(String gender, int sampleSize) {
		ArrayList<String> list = new ArrayList<String>();
		
        for(int i = 1; i <= sampleSize; i++) {
            list.add(gender + new Integer(i));
        }
        
        Collections.shuffle(list);
        
		return list;
	}
}
