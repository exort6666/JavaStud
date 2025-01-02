import java.util.Scanner;
import java.util.Locale;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Task1
{
    public static void main (String[] args) throws UnsupportedEncodingException
    {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Locale.setDefault(new Locale("ru", "RU"));
        Scanner console = new Scanner(System.in);
        System.out.println("Введите целое число: ");
        try
        {
            int n = console.nextInt();
            int iterations = 0;
            while (n != 1)
            {
                if (n%2 == 0){
                    n = n/2;
                }
                else{
                    n = 3*n+1;
                }
                iterations+=1;
            }
            System.out.println("Число итераций " + iterations);
        }
        catch (java.util.InputMismatchException e)
        {
            System.out.println("Ошибка:Введено не целое число");
        }
    }
}
