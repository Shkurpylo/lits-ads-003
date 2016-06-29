import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anatolii on 28.06.16.
 */
public class ijones {

    private static String PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {

        FlagstoneAisle aisle = readFromFileToList(PATH + "/testcases/05.in");
        BigInteger result = FindAllPossibleTracks(aisle);
        System.out.println(result);
        writeToFile(result);

    }
    private static BigInteger FindAllPossibleTracks(FlagstoneAisle aisle){

        String[] letters = aisle.getLetters();
        int columns = aisle.getColumns();
        int rows = aisle.getRows();
        BigInteger[][] resultsMatrix = new BigInteger[rows][columns];
        Map<Character, BigInteger> allPathToLetterCount = new HashMap<>();
        char[] allChars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(Character ch : allChars){
            allPathToLetterCount.put(ch, BigInteger.ZERO);
        }

        //set result in first column
        for(int i = 0; i < rows; i++){
            Character letter  = letters[i].charAt(0);
            allPathToLetterCount.put(letter, allPathToLetterCount.get(letter).add(BigInteger.ONE));
            resultsMatrix[i][0] = BigInteger.ONE;
        }
        // set result in other columns
        for(int j = 1; j < columns; j++){
            for(int i = 0; i < rows; i++){
                resultsMatrix[i][j] = allPathToLetterCount.get(letters[i].charAt(j));
                if(letters[i].charAt(j) != letters[i].charAt(j - 1)){
                    resultsMatrix[i][j] = resultsMatrix[i][j].add(resultsMatrix[i][j-1]);
                }
            }
            //after column result iteration, add new possible path to existing, for each letter;
            for(int i = 0; i < rows; i++){
                Character letter = letters[i].charAt(j);
                allPathToLetterCount.put(letter, allPathToLetterCount.get(letter).add(resultsMatrix[i][j]));
            }
        }

        BigInteger allPossibleTracks = rows == 1 ? resultsMatrix[rows-1][columns-1] :
                resultsMatrix[0][columns-1].add(resultsMatrix[rows-1][columns-1]);

        return allPossibleTracks;
    }

    private static FlagstoneAisle readFromFileToList(String pathToFile) throws IOException{

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))){
            String line;
            line = br.readLine();
            String[] arr = line.split(" ");
            int columns = Integer.parseInt(arr[0]);
            int rows = Integer.parseInt(arr[1]);
            FlagstoneAisle aisle = new FlagstoneAisle(columns, rows);
            for(int i = 0; i < rows; i++){
                String string = br.readLine();
                aisle.getLetters()[i] = string;
            }
            br.close();
            return aisle;
        }
    }

    private static void writeToFile(BigInteger solution) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "/ijones.out", false));
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
