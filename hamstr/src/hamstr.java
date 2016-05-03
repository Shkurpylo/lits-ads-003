import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by anatolii on 30.04.16.
 */
public class hamstr
{
    static String path = System.getProperty("user.dir");
    static int available_food;
    static int hamsters_quantity;
    static int[] need_when_alone;
    static int[] need_in_group;
    static ArrayList<int[]> arrays;

    public static void main(String[] args)
    {
        read_from_file();
        arrays = create_list_of_arrays(need_when_alone, need_in_group);
        selection_sort(need_in_group, arrays);
        //quick_sort(need_in_group);
        int result = count_hamsters(available_food, need_when_alone, need_in_group);
        write_to_file(result);
        //System.out.println(result);

    }

    static void read_from_file()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/hamstr.in"))) {
            String line;
            int counting_line = 0;
            while ((line = br.readLine()) != null) {
                if (counting_line == 0) {
                    available_food = Integer.parseInt(line);
                    counting_line++;
                } else if (counting_line == 1) {
                    hamsters_quantity = Integer.parseInt(line);
                    create_hamsters_lists(hamsters_quantity);
                    counting_line++;
                } else {
                    String[] string = line.split(" ");
                    counting_line++;
                    set_hamster_lists(string, counting_line);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("There is no file in current directory...");
        }
    }
    static void create_hamsters_lists(int size)
    {
        need_when_alone = new int[size];
        need_in_group = new int[size];
    }
    static ArrayList<int[]> create_list_of_arrays(int[] arr, int[] second_arr)
    {
        ArrayList<int[]> arrays = new ArrayList<int[]>();
        arrays.add(arr);
        arrays.add(second_arr);
        return arrays;
    }
    static void set_hamster_lists(String[] str_arr, int index)
    {
        int i = index - 3;
        boolean is_first_num = true;
        for(String st : str_arr)
        {
            if(is_first_num) {
                need_when_alone[i] = Integer.parseInt(st);
                is_first_num = false;
            }
            else
                need_in_group[i] = Integer.parseInt(st);
        }
    }

    static void selection_sort(int[] total_array, ArrayList<int[]> arrays)
    {
        for(int i = 0; i < total_array.length - 1; i++)
        {
            int min_index = i;
            for(int j = i + 1; j < total_array.length; j++)
            {
                if (total_array[min_index] > total_array[j])
                    min_index = j;
            }
            multi_swap(arrays, i, min_index);
        }
    }
    public static void quick_sort(int[] arr)
    {
        quick_sort_recursive(arr, 0, arr.length - 1);
    }
    static void quick_sort_recursive(int[] arr, int left, int right)
    {
        int pivot = get_pivot(arr, left, right);
        int left_write_pos = left;
        int right_write_pos = right;

        while(left_write_pos <= right_write_pos)
        {
            while (arr[left_write_pos] > pivot)
                left_write_pos++;
            while (pivot > arr[right_write_pos])
                right_write_pos--;
            if (left_write_pos <= right_write_pos)
            {
                multi_swap(arrays, left_write_pos, right_write_pos);
                left_write_pos++;
                left_write_pos--;
            }
            if (left < left_write_pos - 1)
                quick_sort_recursive(arr, left, left_write_pos - 1);
            if (left_write_pos < right)
                quick_sort_recursive(arr, left_write_pos, right);
        }

    }
    static int get_pivot(int[] arr, int left, int right)
    {
        return arr[left];
    }

    static void multi_swap(ArrayList<int[]> arrays, int first_index, int second_index)
    {
        for(int[] array : arrays) {
            int tmp = array[first_index];
            array[first_index] = array[second_index];
            array[second_index] = tmp;
        }
    }
    static int count_hamsters(int food, int[] alone, int[] group)
    {
        long food_summ = 0;
        long food_when_alone = 0;
        long food_in_group = 0;
        int number_of_hamster = 0;

        while (number_of_hamster < alone.length)
        {
            food_when_alone += alone[number_of_hamster];
            food_in_group += group[number_of_hamster];
            if(number_of_hamster == 0)
                food_summ = food_when_alone;
            else
                food_summ = (food_when_alone + food_in_group * (number_of_hamster));

            if(food_summ <= food)
                number_of_hamster++;
            else
                return number_of_hamster;
        }
        return number_of_hamster;
    }
    static void write_to_file(int solution)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/discnt.out", false));
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
