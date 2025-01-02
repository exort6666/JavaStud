import java.util.Locale;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
public class Task4
{
    public static void main(String[] args) throws UnsupportedEncodingException{
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Locale.setDefault(new Locale("ru", "RU"));
        Scanner console = new Scanner(System.in);
        System.out.println("Введите количество дорог");
        int quantity = console.nextInt();
        int Max = 0,t = 0;
        for (int i = 0;i<quantity;i++){
            System.out.println("Введите количество туннелей");
            int tunnels = console.nextInt();
            int Min = Integer.MAX_VALUE;
            for (int j = 0;j<tunnels;j++){
                System.out.println("Введите высоту туннеля");
                int height = console.nextInt();
                if (Min>height){
                    Min = height;
                }
            }
            if (Min>Max){
                Max = Min;
                t = i+1;
            }
        }
        System.out.println(t + " " + Max);
    }
}
