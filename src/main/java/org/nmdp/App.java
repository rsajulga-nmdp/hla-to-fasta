package org.nmdp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    static Map<String, PrintWriter> printMap = new HashMap<>();
    private static final String DIVIDER = "_";
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = args[0];
        File input = new File(inputFile);
        String fileName = input.getName().substring(0, input.getName().lastIndexOf("."));
        Scanner sc = new Scanner(new File(inputFile));
        
        for(int lineNum=1; sc.hasNext(); lineNum++){
        	try {
	            String[] data = sc.nextLine().split(",");
	//            String folderName = data[0];
	            String projectName = data[1];
	            String sampleID = data[2];
	            String alleleID = data[3];
	            String alleleName = data[4];
	//            String glString = data[5];
	            
	            if (data.length > 3){
	                StringBuilder sb = new StringBuilder();
	                sb.append(">");
	                sb.append(sampleID);
	                sb.append(DIVIDER);
	                sb.append(projectName);
	                sb.append(DIVIDER);
	                sb.append(alleleID);
	                sb.append(DIVIDER);
	                sb.append(alleleName);
	                sb.append("\n");
	                for(int i = 6; i < data.length; i++){
	                    sb.append(data[i]);
	                }
	                if (alleleName.indexOf('-') >= 0){
	                    String locus = getKey(alleleName);
	                    if (locus.length() > 0){
	                        if(!printMap.containsKey(locus)){
	                            printMap.put(locus, new PrintWriter(fileName + "_"+ locus + ".fasta"));
	                        }
	                        printMap.get(locus).println(sb.toString());
	                    }
	                }
	            }
        	} catch (Exception e) {
        		System.out.println("Something went wrong at line number " + lineNum);
        		System.out.println(e);
        	}
        }

        for(PrintWriter pw : printMap.values()){
            pw.close();
        }
        sc.close();

    }

    private static String getKey(String hla){

        String type = hla.split("\\*")[0];
        if (type.split("-").length < 2){
            return "";
        }
        String key = type.split("-")[1];
        return key;
    }
}
