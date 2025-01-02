import java.util.Locale;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
public class Task2
{
    public static void main (String[] args) throws UnsupportedEncodingException
    {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Locale.setDefault(new Locale("ru", "RU"));
        Scanner console = new Scanner(System.in);
        System.out.println("Введите целое число: ");
        int n = console.nextInt();
        int number;
        int sum = 0;
        for (int i = 0; i<n;i++){
            number = console.nextInt();
            if (i%2 == 0)
            {
                sum+= number;
            }
            else
            {
                sum-= number;
            }
        }
        System.out.println("Сумма знакочередующегося ряда " + sum);
    }
}
