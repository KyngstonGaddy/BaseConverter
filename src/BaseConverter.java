import javax.swing.JFileChooser;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Math;

/**
 * Converts numbers from bases 2-16 into a new number in a new base form
 * @version 12.01.23
 * @author 25gaddy
 * These are the resources I used for the JFileChooser extra: (https://www.geeksforgeeks.org/java-swing-jfilechooser/) (https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JFileChooser.html) (https://www.javatpoint.com/java-jfilechooser)
 */
public class BaseConverter{
    private final String ALNUM = "0123456789ABCDEF";

    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the num used for the conversion
     * @param fromBase original base value
     * @return sum of the numbers
     */
    public int strToInt(String num, String fromBase)    {
        int sum = 0, exp = 0;
        int base = Integer.parseInt(fromBase);
        for(int i = num.length()-1; i >= 0; i--)    {
            sum += ALNUM.indexOf(num.charAt(i)) * Math.pow(base, exp++);
        }
        return sum;
    }

    /**
     * Convert a base-10 int to a String number of base toBase.
     * @param num the num used to convert into a string
     * @param toBase converts into a new base
     * @return the new number in the new base form
     */
    public String intToStr(int num, int toBase) {
        String sum = "";
        if(num == 0)
            return "0";
        while(num > 0)  {
            sum = ALNUM.charAt(num % toBase) + sum;
            num /= toBase;
        }
        return sum;
    }

    /**
     * Opens the file stream, inputs data one line at a time, converts, prints
     * the result to the console window and writes data to the output stream.
     * EXTRA: JFileChooser to choose file from your desktop to use
     */
    public void inputConvertPrintWrite()    {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int file = fileChooser.showOpenDialog(null); // for some reason, I get a weird NullPointerException if I don't keep this line of code
            File chosenFile = fileChooser.getSelectedFile();
            // input
            Scanner in = new Scanner(chosenFile);
            PrintWriter pw = new PrintWriter(new File("datafiles/converted.dat"));
            String[] temp;
            while(in.hasNext()) {
                temp = in.nextLine().split("\t");
                int fromBase = Integer.parseInt(temp[1]);
                int toBase = Integer.parseInt(temp[2]);
                if( fromBase< 2 || fromBase > 16)
                    System.out.println("Invalid input base " + temp[1]);
                else if (toBase < 2 || toBase > 16)
                    System.out.println("Invalid output base " + temp[2]);
                else { // all is good, do the work
                    System.out.println(temp[0] + " base " + temp[1] + " = " + intToStr(strToInt(temp[0], temp[1]), toBase) + " base " + temp[2]);
                    pw.println(temp[0] + "\t" + temp[1] + "\t" + intToStr(strToInt(temp[0], temp[1]), toBase) + "\t" + temp[2]);
                }
            }
            in.close();
            pw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again");
        }
    }

    /**
     * Main method for program
     * @param args command line arguments, if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}
