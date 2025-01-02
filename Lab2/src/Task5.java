import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Task5 {
    public static String targetSum(int[] array,int target){
        for (int i = 0; i<array.length; i++){
            for (int j = i+1; j<array.length; j++){
                if (array[i]+array[j]==target){
                    return (array[i]+" "+array[j]);
                }
            }
        } return null;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner console = new Scanner(System.in);
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.out.println("Введите число target");
        int target = console.nextInt();
        System.out.println("Введите размерность массива");
        int n = console.nextInt();
        int[] array = new int[n];
        System.out.println("Введите элементы массива");
        for (int i = 0;i<n;i++){
            array[i] = console.nextInt();
        }
        System.out.println(targetSum(array,target));
    }
}
