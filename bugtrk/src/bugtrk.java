import java.io.*;
import java.text.DecimalFormat;
import java.math.BigInteger;

//on the conditions of verification, the class name should be only like this
public class bugtrk {

    final static String PATH = System.getProperty("user.dir");
    static long quantity;
    static long width;
    static long hight;


    public static void main(String[] args) {
        getCondition(readFromFile());
        BigInteger areaOfAllPages = calcArea(quantity, width, hight);
        BigInteger minSquareSide = countSideSize(areaOfAllPages, quantity, width, hight);
        writeToFile(minSquareSide);
    }


    private static BigInteger countSideSize(BigInteger area, long quantity, long width, long hight) {
        BigInteger bigTwo = BigInteger.valueOf(2);
        BigInteger bigQuantity = BigInteger.valueOf(quantity);
        BigInteger biggestPageSide = BigInteger.valueOf(width > hight ? width : hight);
        BigInteger bigSmallestPageSize = BigInteger.valueOf(width < hight ? width : hight);
        BigInteger probablySideSize = biggestPageSide.multiply(bigQuantity);
        BigInteger sideCapacity = (probablySideSize.divide(biggestPageSide)).divide(bigTwo);
        
        if (biggestPageSide.compareTo(bigSmallestPageSize.multiply(bigQuantity)) >= 0) {
            return biggestPageSide;
        } else if (quantity == 1) {
            return biggestPageSide;
        } else if (quantity == 2) {
            return bigSmallestPageSize.multiply(bigTwo);
        } else {
            while ((probablySideSize.multiply(probablySideSize)).compareTo(area) >= 0) {
                if ((probablySideSize.subtract(sideCapacity.multiply(biggestPageSide)).multiply(probablySideSize.subtract(sideCapacity.multiply(biggestPageSide))).compareTo(area) >= 0)) {
                    probablySideSize = probablySideSize.subtract(biggestPageSide.multiply(sideCapacity));
                    sideCapacity = sideCapacity.divide(bigTwo);
                } else {
                    probablySideSize = probablySideSize.subtract(biggestPageSide);
                }
            }
        }

        return  probablySideSize.add(biggestPageSide);
    }


    private static BigInteger calcArea(long quantity, long width, long hight) {
        BigInteger bigQuantity = BigInteger.valueOf(quantity);
        BigInteger bigWidth = BigInteger.valueOf(width);
        BigInteger bigHight = BigInteger.valueOf(hight);
        BigInteger area = bigQuantity.multiply(bigHight).multiply(bigWidth);
        return area;
    }


    private static void getCondition(String[] strings) {
        quantity = Long.parseLong(strings[0]);
        width = Long.parseLong(strings[1]);
        hight = Long.parseLong(strings[2]);
    }


    private static String[] readFromFile() {
        String[] strings = new String[3];
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + "/bugtrk.in"))) {
            String line;
            line = br.readLine();
            strings = line.split(" ");
            br.close();
        } catch (IOException e) {
            System.out.println("There is no file in current directory...");
        }
        return strings;
    }


    private static void writeToFile(BigInteger solution) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "/bugtrk.out", false));
            DecimalFormat df = new DecimalFormat("#0");
            String dx = df.format(solution);
            writer.write(dx);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.print("Exception in writting file");
        }
    }
}
