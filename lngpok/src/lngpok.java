import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeSet;


public class lngpok {

    final static String PATH = System.getProperty("user.dir");
    static int jokers;

    public static void main(String[] args){
        TreeSet<Integer> cards = getListOfIntFromStringArray(readFromFile());
        int result = maxConsistency(cards);
        writeToFile(result);
        //System.out.println(result);
    }


    private static int maxConsistency(TreeSet<Integer> sortedCards) {
        if (sortedCards.size() == 0) {                       // if we have jokers only
            return jokers;
        } else if (sortedCards.size() == 1) {               // if we have only one card
            return jokers + 1;
        }

        ArrayList<Integer> cloneOfSortedCards = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<>();

        for(int card : sortedCards) {
            cloneOfSortedCards.add(card);
        }

        for(int i = 0; i < cloneOfSortedCards.size() - 1; i++) {
            int result = 1;
            int availableJokers = jokers;
            int diference = 1;

            int j = i;
            while (availableJokers >= 0 && j < cloneOfSortedCards.size() - 1) {
                int thisCard = cloneOfSortedCards.get(j + 1);
                int previousCard = cloneOfSortedCards.get(j);
                diference = thisCard - previousCard - 1;

                if (availableJokers - diference >= 0) {
                    availableJokers -= diference;
                    result += 1 + diference;
                } else {
                    break;
                }

                j++;
            }

            if (availableJokers > 0) {
                result += availableJokers;
            }

            results.add(result);
        }

        int maxConsistency = 0;

        for (int result : results) {
            maxConsistency = maxConsistency < result ? result : maxConsistency;
        }

        return maxConsistency;
    }


    private static TreeSet<Integer> getListOfIntFromStringArray(String[] strings) {
        TreeSet<Integer> cards = new TreeSet<Integer>();
        for(String st : strings) {
            int card = Integer.parseInt(st);
            if (card == 0) {
                jokers++;
            } else {
                cards.add(card);
            }
        }
        return cards;
    }


    private static String[] readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + "/lngpok.in"))) {
            String line = br.readLine();
            String[] tmp = line.split(" ");
            br.close();
            return tmp;
        }
        catch (IOException e)
        {
            System.out.println("There is no file in current directory...");
        }
        return null;
    }


    private static void writeToFile(double solution) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "/lngpok.out", false))) {
            DecimalFormat df = new DecimalFormat("#0");
            String dx = df.format(solution);
            writer.write(dx);
            writer.flush();
            writer.close();
        }  catch (IOException e) {
            System.out.print("Exception in writting file");
        }
    }
}
