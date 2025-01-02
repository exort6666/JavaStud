import java.io.PrintStream;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;

public class Task6 {
    public static int sumOfTwoDimArray(int[][] array){
        int sum = 0;
        for (int j = 0; j<array[0].length;j++){
            for (int i = 0; i<array.length;i++){
                sum+=array[i][j];
            }
        } return sum;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner console = new Scanner(System.in);
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.out.println("Введите размерность двумерного массива (x на y):");
        int x = console.nextInt();
        int y = console.nextInt();
        int[][] array = new int[x][y];
        System.out.println("Введите элементы массива (построчно):");
        for (int j = 0; j<y; j++){ //y так как задаем по строкам, а не по столбцам
            for (int i = 0; i<x; i++){
                array[i][j]= console.nextInt();
            }
        }
        System.out.println(sumOfTwoDimArray(array));
    }
}