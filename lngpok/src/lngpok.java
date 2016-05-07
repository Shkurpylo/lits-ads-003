import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by anatolii on 04.05.16.
 */
public class lngpok
{
    static String path = System.getProperty("user.dir");
    static int jokers;

    public static void main(String[] args)
    {
        TreeSet<Integer> cards = getArrayListOfIntFromStringArray(readFromFile());
        int result = maxConsistency(cards);
        writeToFile(result);
        //System.out.println(result);

    }

    static int maxConsistency(TreeSet<Integer> sorted_cards)
    {
        if (sorted_cards.size() == 0)                        // if we have jokers only
            return jokers;
        else if (sorted_cards.size() == 1)                    // if we have only one card
            return jokers + 1;

        ArrayList<Integer> clone_of_sorted_cards = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<Integer>();

        for(int card : sorted_cards) {
            clone_of_sorted_cards.add(card);
        }
        for(int i = 0; i < clone_of_sorted_cards.size() - 1; i++)
            {
                int result = 1;
                int available_jokers = jokers;
                int diference = 1;
                int j = i;
                while (available_jokers >= 0 && j < clone_of_sorted_cards.size() - 1)
                {
                    int this_card = clone_of_sorted_cards.get(j + 1);
                    int previous_card = clone_of_sorted_cards.get(j);
                    diference = this_card - previous_card - 1;
                    if (available_jokers - diference >= 0) {
                        available_jokers -= diference;
                        result += 1 + diference;
                    }
                    else
                        break;

                    j++;

                }
                if (available_jokers > 0)
                    result += available_jokers;
                results.add(result);
            }
        int max_consistency = 0;
        for (int result : results)
        {
            max_consistency = max_consistency < result ? result : max_consistency;
        }
        return max_consistency;
    }


    static TreeSet<Integer> getArrayListOfIntFromStringArray(String[] strings)
    {
        TreeSet<Integer> cards = new TreeSet<Integer>();
        for(String st : strings)
        {
            int card = Integer.parseInt(st);
            if (card == 0)
                jokers++;
            else
                cards.add(card);
        }
        return cards;
    }

    static String[] readFromFile()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/lngpok.in")))
        {
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
    static void writeToFile(double solution)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/lngpok.out", false));
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
