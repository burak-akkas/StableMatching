// ds imports
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) {
		
		// get args
		// matching people
		if(args.length == 4) {
			System.out.println("File paths: " + args[1] + ", " + args[3]);
			System.out.println("Processing files...");
			
			// -w : 0, woman_path : 1, -m : 2, man_path : 3
			String womenPreferencesFilePath = args[1];
			String menPreferencesFilePath = args[3];
			
			// use HashMap to store key, value pairs
			HashMap<String, List<String>> womenPreferences = new HashMap<String, List<String>>();
			HashMap<String, List<String>> menPreferences = new HashMap<String, List<String>>();

			// read files into HashMap using FileHandler class
			womenPreferences = FileHandler.readFileToHashMap(womenPreferencesFilePath);
			menPreferences = FileHandler.readFileToHashMap(menPreferencesFilePath);
			
			// woman and man count must be equal
			if(womenPreferences.size() == menPreferences.size()) {
				System.out.println("Reading complete. \nStable matching began...");
				
				// runtime analysis start
				double startTime = System.nanoTime(); 
				
				// calculate matches
				HashMap<String, String> matches = match(menPreferences, womenPreferences);
				
				// runtime analysis end
				double estimatedTime = (System.nanoTime() - startTime) / 1000000;
				
				// write to output.txt using FileHandler class 
				FileHandler.writeHashMapToFile(matches);
				
				// program completed
				System.out.println("Algorithm took " + estimatedTime + " milliseconds to complete.");
				System.out.println("Results are located at 'output.txt' in working directory.");
				
			} else {
				System.err.println("Dataset error: Woman and man count must be equal.");
			}
		
		// generating samples
		} else if(args.length == 2) {
			
			// get sample size input
			int sampleSize = Integer.parseInt(args[1]);
			
			System.out.println("Generating sample data for " + sampleSize + " people...");
			
			// use sample generator class and write to working directory
			SampleGenerator.generateSampleData("m", sampleSize);
			SampleGenerator.generateSampleData("w", sampleSize);
			
			System.out.println("Sample data generation completed. \nOutput files are located at current working directory\nw" + sampleSize + ".txt, m" + sampleSize + ".txt");
		
		// bad arguments
		} else {
			System.err.println("Please specify argument like following: \nFor matching: \nmatch.jar -w woman_textfile.txt -m man_textfile.txt\nFor generating samples: \nmatch.jar -s sampleDataSize");
		}
		
	}
	
	static HashMap<String, String> match(HashMap<String, List<String>> menPreferences, HashMap<String, List<String>> womenPreferences) {
		
		// use hashmap to store key, value pairs
		HashMap<String, String> matchedCouples = new HashMap<String, String>();
		
		// use array to store all man
		List<String> availableMen = new ArrayList<String>(menPreferences.keySet());
		
		// while all man not stable matched
		while(!availableMen.isEmpty()) {
			
			// choose a such a man currentMan
			String currentMan = availableMen.remove(0);
			
			// get currentMan's woman preferences as currentPreferences
			List<String> currentPreferences = menPreferences.get(currentMan);
			
			// first woman is most preferred
			for(String woman : currentPreferences) {
				
				// if woman is free
				if(matchedCouples.get(woman) == null) {
					
					// currentMan and woman matched
					matchedCouples.put(woman, currentMan);
					
					break;
				
				} else { // if woman is not free, currently engaged to 'other'
					String other = matchedCouples.get(woman);
					List<String> currentWomansPreferences = womenPreferences.get(woman);
					
					// if woman prefers 'current' to 'other'
					if(currentWomansPreferences.indexOf(currentMan) < currentWomansPreferences.indexOf(other)){
						matchedCouples.put(woman, currentMan);
						availableMen.add(other);
						
						break;
					} // if not, m remains free. Operation not needed.
				}
			}
		}
		return matchedCouples;
	}

}