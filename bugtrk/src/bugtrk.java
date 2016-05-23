import java.io.*;
import java.text.DecimalFormat;
import java.math.BigInteger;

/**
 * Created by anatolii on 18.05.16.
 */
public class bugtrk
{
    static String path = System.getProperty("user.dir");
    static long quantity;
    static long width;
    static long hight;

    public static void main(String[] args)
    {
        getCondition(read_from_file());
        BigInteger area_of_all_pages = calcarea(quantity, width, hight);
        BigInteger min_square_side = count_side_size(area_of_all_pages, quantity, width, hight);
        write_to_file(min_square_side);
        //System.out.println(min_square_side);

    }
    static BigInteger count_side_size(BigInteger area, long quantity, long width, long hight)
    {
        BigInteger big_quantity = BigInteger.valueOf(quantity);
        BigInteger biggest_page_side = BigInteger.valueOf(width > hight ? width : hight);
        long smallest_page_size = width < hight ? width : hight;
        BigInteger big_two = BigInteger.valueOf(2);
        BigInteger big_smallest_page_size = BigInteger.valueOf(width < hight ? width : hight);
        BigInteger probably_side_size = biggest_page_side.multiply(big_quantity);
        BigInteger side_capasity = (probably_side_size.divide(biggest_page_side)).divide(big_two);
        if (biggest_page_side.compareTo(big_smallest_page_size.multiply(big_quantity)) >= 0)
            return biggest_page_side;
        else if (quantity == 1)
            return biggest_page_side;
        else if (quantity == 2)
            return BigInteger.valueOf(smallest_page_size*2);
        else
        {
            while ((probably_side_size.multiply(probably_side_size)).compareTo(area) >= 0) {
                if ((probably_side_size.subtract(side_capasity.multiply(biggest_page_side)).multiply(probably_side_size.subtract(side_capasity.multiply(biggest_page_side))).compareTo(area) >= 0)) {
                    probably_side_size = probably_side_size.subtract(biggest_page_side.multiply(side_capasity));
                    side_capasity = side_capasity.divide(big_two);
                }
                else {
                    probably_side_size = probably_side_size.subtract(biggest_page_side);
                }
            }

        }
        return  probably_side_size.add(biggest_page_side);
    }

    static BigInteger calcarea(long quantity, long width, long hight)
    {
        BigInteger big_quantity = BigInteger.valueOf(quantity);
        BigInteger big_width = BigInteger.valueOf(width);
        BigInteger big_hight = BigInteger.valueOf(hight);

        BigInteger a = big_quantity.multiply(big_hight).multiply(big_width);
        return a;
    }

    static void getCondition(String[] strings)
    {
        quantity = Long.parseLong(strings[0]);
        width = Long.parseLong(strings[1]);
        hight = Long.parseLong(strings[2]);
    }
    static String[] read_from_file()
    {
        String[] strings = new String[3];
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/bugtrk.in")))
        {
            String line;
            line = br.readLine();
            strings = line.split(" ");
            br.close();
        } catch (IOException e) {
            System.out.println("There is no file in current directory...");
        }
        return strings;
    }
    static void write_to_file(BigInteger solution)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/bugtrk.out", false));
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
