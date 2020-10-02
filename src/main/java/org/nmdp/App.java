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



        while(sc.hasNext()){
            String[] data = sc.nextLine().split(",");
            StringBuilder sb = new StringBuilder();
            sb.append(">");
            sb.append(data[1]);
            sb.append(DIVIDER);
            sb.append(data[2]);
            sb.append(DIVIDER);
            sb.append(data[3]);
            sb.append("\n");
            for(int i = 5; i < data.length; i++){
                sb.append(data[i]);
            }
            String key = getKey(data[3]);
            if(!printMap.containsKey(key)){
                printMap.put(key, new PrintWriter(fileName + "_"+ key + ".fasta"));
            }
            printMap.get(key).println(sb.toString());

        }

        for(PrintWriter pw : printMap.values()){
            pw.close();
        }
        sc.close();

    }

    private static String getKey(String hla){

        String type = hla.split("\\*")[0];
        String key = type.split("-")[1];
        return key;
    }
}
