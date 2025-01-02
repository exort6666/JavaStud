import java.util.Locale;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
public class Task3
{
    public static void main (String[] args) throws UnsupportedEncodingException{
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Locale.setDefault(new Locale("ru", "RU"));
        Scanner console = new Scanner(System.in);
        System.out.println("Введите координату x");
        int x = console.nextInt();
        System.out.println("Введите координату y");
        int y = console.nextInt();
        String t;
        int x0 = 0, y0 = 0, step = 0, quantity;
        console.nextLine();
        while(console.hasNext()){
            t = console.nextLine();
            if (t.equals("север")){
                quantity = console.nextInt();
                console.nextLine();
                x0+=quantity;
            } else if (t.equals("юг")){
                quantity = console.nextInt();
                console.nextLine();
                x0-=quantity;
            } else if (t.equals("запад")){
                quantity = console.nextInt();
                console.nextLine();
                y0-=quantity;
            } else if (t.equals("восток")){
                quantity = console.nextInt();
                console.nextLine();
                y0+=quantity;
            } else if (t.equals("стоп")) {
                break;
            } else {
                System.out.println ("Error:некорректный ввод данных");
                break;
            }
            step+=1;
            if (x0==x && y0==y){
                break;
            }
        }
        if (x0==x && y0==y){
            System.out.println(step);
        }
        else {
            System.out.println("Ошибка: До клада не дойти");
        }
    }
}
