import java.util.Locale;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Task5 {
    public static void main (String[] args) throws UnsupportedEncodingException{
        Locale.setDefault(new Locale("ru", "RU"));
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Scanner console = new Scanner(System.in);
        int x = console.nextInt();
        int x1 = x/100,x2 = (x%100)/10,x3 = x%10;
        if ((x1+x2+x3)%2==0 && (x1*x2*x3)%2==0){
            System.out.println("Число является дважды чётным");
        } else{
            System.out.println("Число НЕ является дважды чётным");
        }
    }
}
