
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by anatolii on 23.06.16.
 */
public class wchain {

    private static final String PATH = System.getProperty("user.dir");

    public static void main(String[] args){

        ArrayList<String> listOfWords = readFromFileToList(PATH + "/wchain.in");
        //Comparator, which will sort the Strings by length, from biggest to smaller
        Comparator<String> comparator = (str1, str2) -> Integer.compare(str1.length(), str2.length());
        //Sort the list
        Collections.sort(listOfWords, comparator);
        int result = maximumSizeChain(listOfWords);
        writeToFile(result);

        //System.out.println(result);

        
    }

    static int maximumSizeChain(ArrayList<String> listOfWords){

        List<Integer> resultList = new ArrayList<>(listOfWords.size());
        String firstWord = listOfWords.get(0);
        int length = listOfWords.get(0).length();
        int maxChainLength = 1;

        for(String word : listOfWords){
            resultList.add(1);
        }

        for(int j = 1; j < listOfWords.size(); j++){
            String currentWord = listOfWords.get(j);
            if(currentWord.length() - length >= 2){
                length = listOfWords.get(j-1).length();
            }
            if(currentWord.length() - length == 1){
                int k = j - 1;
                int max = 1;
                while(k >= 0 && listOfWords.get(k).length() >= length) {
                        String string = listOfWords.get(k);
                        max = resultList.get(k) + 1 > resultList.get(j)
                                ? resultList.get(k) + 1 : resultList.get(j);
                        if (compare(currentWord, string)) {
                            resultList.set(j, max);
                            maxChainLength = Integer.max(maxChainLength, max);
                        }
                    k--;
                }
            }

        }
        System.out.print(" ");
        return maxChainLength;
    }

    static boolean compare(String stringBiggestOne, String stringSmallestTwo){
        boolean isSequince = false;
        if(stringBiggestOne.contains(stringSmallestTwo)){
            return true;
        }

        for(int i =0; i < stringBiggestOne.length(); i++){
            StringBuilder sb = new StringBuilder(stringBiggestOne);
            String string = sb.deleteCharAt(i).toString();
            if(string.equals(stringSmallestTwo)){
                isSequince = true;
                return isSequince;
            }
        }

        return isSequince;
    }


    static ArrayList readFromFileToList(String pathToFile){
        ArrayList<String> arrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))){
            String line;
            int capacity = 0;
            boolean is_first_line = true;
            while ((line = br.readLine()) != null) {
                if (is_first_line){
                    capacity = Integer.parseInt(line);
                    is_first_line = false;
                } else {
                    arrayList.add(line);
                }
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("There is no file in current directory...");
        }
        return arrayList;
    }

    static void writeToFile(int solution) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "/wchain.out", false));
            DecimalFormat df = new DecimalFormat("#0");
            String dx = df.format(solution);
            writer.write(dx);
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            System.out.print("Exception in writting file");
        }
    }
}
