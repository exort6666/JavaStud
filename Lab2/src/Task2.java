import java.util.Arrays;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.io.PrintStream;

public class Task2 {
    public static int[] sortArrayOfANYTwoArray(int[] array1,int[] array2){
        //Данный метод работает ДЛЯ ЛЮБЫХ массивов.
        //Ниже будет приведен метод без использования метода sort
        int[] array = new int[array1.length+array2.length];
        for (int i = 0; i<array1.length;i++){
            array[i] = array1[i];
        }
        for (int i = 0; i<array2.length;i++){
            array[i+array1.length] = array2[i];
        }
        Arrays.sort(array);
        return array;
   }
   public static int[] sortArrayOfSORTTwoArray(int[] array1,int[] array2){
        //Метод для работы с ОТСОРТИРОВАННЫМИ массивами
       int[] array = new int[array1.length+array2.length];
       int i = 0,j = 0;
       while (i+j<array1.length+array2.length){
           if (i==array1.length){
               array[i+j] = array2[j];
               j++;
               continue;
           } if (j==array2.length){
               array[i+j] = array1[i];
               i++;
               continue;
           } if (array1[i]<=array2[j]){
               array[i+j] = array1[i];
               i++;
               continue;
           } if (array2[j]<=array1[i]){
               array[i+j] = array2[j];
               j++;
               continue;
           }
       }
       return array;
   }
    public static void main (String[] args) throws UnsupportedEncodingException
    {
        Scanner console = new Scanner(System.in);
        System.setOut(new PrintStream(System.out,true,"UTF-8"));
        System.out.println("Введите размерность массива 1: ");
        int n1 = console.nextInt();
        int[] array1 = new int[n1];
        System.out.println("Введите элементы массива 1: ");
        for (int i = 0; i<n1;i++){
            array1[i] = console.nextInt();
        }
        System.out.println("Введите размерность массива 2: ");
        int n2 = console.nextInt();
        int[] array2 = new int[n2];
        System.out.println("Введите элементы массива 2: ");
        for (int i = 0; i<n2;i++){
            array2[i] = console.nextInt();
        }
        System.out.println("Полученный массив:");
        int[] arrayOfAny = sortArrayOfANYTwoArray(array1,array2);
        int[] arrayOfSort = sortArrayOfSORTTwoArray(array1,array2);
        for (int i = 0;i<arrayOfAny.length;i++){
            System.out.print(arrayOfAny[i]+" ");
        }
        System.out.println();
        for (int i = 0;i<arrayOfSort.length;i++){
            System.out.print(arrayOfSort[i]+" ");
        }
    }
}
