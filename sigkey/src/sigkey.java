import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by anatolii on 24.05.16.
 */
public class sigkey
{
    static String path = System.getProperty("user.dir");
    static int quantity;
    static HashMap<ArrayList<Integer>, Integer> public_keys = new HashMap<ArrayList<Integer>, Integer>();
    static HashMap<ArrayList<Integer>, Integer> private_keys = new HashMap<ArrayList<Integer>, Integer>();

    public static void main(String[] args)
    {
        getCondition();
        int[] summ = get_all_char_array();

        int result = pair_count(public_keys, private_keys, summ);
        write_to_file(result);

    }
    static int pair_count(HashMap<ArrayList<Integer>, Integer> public_k, HashMap<ArrayList<Integer>, Integer> private_k, int[] summ) {
        int result = 0;
        Iterator<Map.Entry<ArrayList<Integer>, Integer>> public_iterator= public_k.entrySet().iterator();
        Iterator<Map.Entry<ArrayList<Integer>, Integer>> private_iterator= private_k.entrySet().iterator();

        while(public_iterator.hasNext()) {
            Map.Entry<ArrayList<Integer>, Integer> public_entry = public_iterator.next();
            int public_key = public_entry.getKey().get(0);
            int public_value = public_entry.getValue();
            for ( Map.Entry<ArrayList<Integer>, Integer> entry_private : private_k.entrySet()) {
                int summ_of_keys = entry_private.getKey().get(0) + public_key;
                int summ_of_values = entry_private.getValue() + public_value;

                if (summ_of_values == summ[summ_of_keys - 2]) {
                    result++;
                    //private_k.remove(entry_private.getKey());
                    break;

                }
            }
        }
        return result;
    }


         /*
        for (int i = 0; i < public_k.size(); i++) {
            for (int j = 0; j < private_k.size(); j++) {
                concated = public_k.get(i).(private_k.get(j));
                int indicator = 0;
                for(int z = 0; z < concated.length(); z++)
                {
                    if (concated.contains(all_char_array[z]))
                        indicator++;
                }
                if(indicator == concated.length())
                {
                    private_k.remove(j);
                    result++;
                }
            }
        }
        */

    static int[] get_all_char_array()
    {
        char[] all_char_array = new char[]
                {
                        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
                };
        int[] ascii_summ = new int[all_char_array.length - 1];

        int summ = all_char_array[0];
        for(int i = 1; i < all_char_array.length; i++)
        {
            summ += all_char_array[i];
            ascii_summ[i - 1] = summ;
        }
        return ascii_summ;
    }


    static void getCondition()
    {
        read_from_file();
    }


    static void read_from_file()
        {
        CharSequence a = "a";
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/06.in"))) {
            String line;
            int counting_line = 0;
            while ((line = br.readLine()) != null) {
                if (counting_line == 0) {
                    quantity = Integer.parseInt(line);
                    counting_line++;
                }
                else {
                    if(line.contains(a)) {
                        int summ = 0;
                        char[] tmp = line.toCharArray();
                        for(char ch: tmp) {
                            summ += ch;
                        }
                        int random2 = (int) (Math.random()*10000000) + 7;
                        ArrayList<Integer> tmparr = new ArrayList<>(3);
                        tmparr.add(0, tmp.length);
                        tmparr.add(1, tmp.hashCode() + tmp.length);
                        tmparr.add(2, random2);

                        if(public_keys.containsKey(tmparr))
                            System.out.println("colision!!!");
                        public_keys.put(tmparr, summ);
                        counting_line++;
                    }
                    else {
                        int summ = 0;
                        char[] tmp = line.toCharArray();
                        for(char ch: tmp) {
                            summ += ch;
                        }
                        int random2 = (int) (Math.random()*10000000) + 7;
                        ArrayList<Integer> tmparr = new ArrayList<>(2);
                        tmparr.add(0, tmp.length);
                        tmparr.add(1, tmp.hashCode());
                        tmparr.add(2, random2);

                        private_keys.put(tmparr, summ);
                        counting_line++;
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("There is no file in current directory...");
        }
    }
    static void write_to_file(int solution)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/sigkey.out", false));
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
