/**
 * Created by anatolii on 12.04.16.
 */

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class discnt
{
    static int[] prices;
    static double discount = 0;
    static String path = System.getProperty("user.dir");


    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);  //change coma on dot
        read_from_file();
        double solution = right_Order(prices, discount);
        write_to_file(solution);
    }


    static double right_Order(int[] arr, double discount)
    {
        int coef = arr.length - (arr.length / 3);
        double summ = 0;
        double summ_with_discount = 0;
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i+1; j < arr.length; j++)
            {
                if (arr[min_index] > arr[j])
                    min_index = j;
            }
            swap(arr, i, min_index);
            if(i < coef)
                summ += arr[i];
            else
                summ_with_discount += (arr[i] * discount);
        }
        return summ + summ_with_discount;
    }


    static void read_from_file()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/discnt.in")))
        {
            String line;
            boolean is_first_line = true;
            while ((line = br.readLine()) != null) {
                if (is_first_line)
                {
                    String[] tmp = line.split(" ");
                    prices = new int[tmp.length];
                    for(int i = 0; i < tmp.length; i++)
                    {
                        prices[i] = (Integer.parseInt(tmp[i].trim()));
                    }
                    is_first_line = false;
                }
                else {
                    double newDouble = (1.0 - (Double.parseDouble(line)/100));
                    discount = new BigDecimal(newDouble).setScale(2, RoundingMode.HALF_UP).doubleValue(); //rounding number
                }
            }
            br.close();
        }
        catch (IOException e)
        {
            System.out.println("There is no file in current directory...");
        }
    }


    static void write_to_file(double solution)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/discnt.out", false));
            DecimalFormat df = new DecimalFormat("#0.00");
            String dx = df.format(solution);
            writer.write(dx);
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            System.out.print("Exception in writting file");
        }
    }


    static void swap(int[] arr, int first_index, int second_index)
    {
        int tmp = arr[first_index];
        arr[first_index] = arr[second_index];
        arr[second_index] = tmp;
    }
}
