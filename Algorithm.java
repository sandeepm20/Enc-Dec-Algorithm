import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;


/*
* This program will either Encode/Decode all the data based on the data provided
* If space is entered as part of the data then the program will treat the data as decoded and will try to encode
* & vice-versa
*/
public class Algorithm {

	static String inputFilePath = "C:\\Users\\tsa19065\\Workspace_Scrap\\Sample\\inputs.txt";
	static HashMap<Character, Character> encDecAlgorithm = new HashMap<Character, Character>();
	static List<String> incomingMessage = new ArrayList<String>();
	static List<String> outgoingMessage = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		try {
			//Construct Algorithm
			constructEncoderDecoderAlgorithm();
			
			//Read data from inputs.txt File
			readInput();
			
			//Based on the data in the input file, make a decision on whether to encode/decode the data
			if(incomingMessage.size() != 0){
				if(incomingMessage.get(0).contains(" ")){
					encode(incomingMessage);
				}
				else{
					decode(incomingMessage);
				}
			}
			
			//Write data to ouptuts.txt File
			writeOutput(outgoingMessage);
		} catch (Exception e) {
			outgoingMessage.add("Error occured while encoding/decoding data provided, please try again!");
			writeOutput(outgoingMessage);
			e.printStackTrace();
		}
	}
	
	/*
	 * Constructing encoder/decoder algorithm from the given input of encoded and decoded lines
	 * */
	private static void constructEncoderDecoderAlgorithm() throws Exception{
		/*
		 * HashMap containing Encoded/Decoded values as Key/Value pairs
		 * */
		HashMap<String, String> algorithm = new HashMap<String, String>();
		algorithm.put("1JKJ'pz'{ol'{yhklthyr'vm'{ol'Jvu{yvs'Kh{h'Jvywvyh{pvu5", "*CDC is the trademark of the Control Data Corporation.");
		algorithm.put("1PIT'pz'h'{yhklthyr'vm'{ol'Pu{lyuh{pvuhs'I|zpulzz'Thjopul'Jvywvyh{pvu5", "*IBM is a trademark of the International Business Machine Corporation.");
		algorithm.put("1KLJ'pz'{ol'{yhklthyr'vm'{ol'Kpnp{hs'Lx|pwtlu{'Jvywvyh{pvu5", "*DEC is the trademark of the Digital Equipment Corporation.");

		Set set = algorithm.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()) {
	       Map.Entry mapEntry = (Map.Entry)iterator.next();
	       String key = (String) mapEntry.getKey();
	       String value = (String) mapEntry.getValue();
	       for (int i = 0; i < key.length(); i++){
	    	    encDecAlgorithm.put(key.charAt(i), value.charAt(i));
	       }
	    }
	}
	
	/*
	 * Read data from given input file
	 * */
	private static void readInput() throws IOException{
		incomingMessage = Files.readAllLines(Paths.get(inputFilePath), StandardCharsets.UTF_8);
	}
	
	/*
	 * Write encoded/decoded data from the given input file into outputs.txt file
	 * */
	private static void writeOutput(List<String> message) throws IOException{
		PrintStream ps = new PrintStream(new FileOutputStream("outputs.txt"));
		for (String s : message) {
			ps.println(s);
		}
		System.setOut(ps);
	}

	/*
	 * EncDecAlgorithm(map) has encoded data in key and decoded data in value; using the data appropriately will help in decoding the encoded data from input file
	 * */
	private static void decode(List<String> msg){
		for (String s : msg) {
			StringBuilder str = new StringBuilder("");
			for (int i = 0; i < s.length(); i++){
				str.append(encDecAlgorithm.get(s.charAt(i)));
	        }
			outgoingMessage.add(str.toString());
		}
	}
	
	/*
	 * EncDecAlgorithm(map) has encoded data in key and decoded data in value; using the data appropriately will help in encoding given data
	 * */
	private static void encode(List<String> msg){
		for (String s : msg) {
			StringBuilder str = new StringBuilder("");
			for (int i = 0; i < s.length(); i++){
				for (Entry<Character, Character> entry : encDecAlgorithm.entrySet()) {
		            if (Objects.equals(s.charAt(i), entry.getValue())) {
		                str.append(entry.getKey());
		            }
		        }
	        }
			outgoingMessage.add(str.toString());
		}
	}
	
}
