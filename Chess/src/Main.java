import java.io.UnsupportedEncodingException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out,true,"UTF-8"));
        Scanner console = new Scanner(System.in);

        Board board = new Board();
        board.start();
        board.setColorGame('w');
        System.out.println();

        boolean isGame = true;

        while (isGame){
            board.printBoard();
            System.out.println();

            System.out.println("Управление:");
            System.out.println("Для хода фигуры из (row col) в (row1 col1) формат ввода:");
            System.out.println("row col row1 col1 (Пример:1 1 3 1)");

            switch (board.getColorGame()){
                case 'w': System.out.println("Ход белых"); break;
                case 'b': System.out.println("Ход черных"); break;
            }

            System.out.print("Введите ход: ");

            // Обработка строки
            String inputLine = console.nextLine();
            int row, col, row1, col1;
            String [] coords = inputLine.split(" ");
            row = Integer.parseInt(coords[0]);
            col = Integer.parseInt(coords[1]);
            row1 = Integer.parseInt(coords[2]);
            col1 = Integer.parseInt(coords[3]);

            // Ход фигуры + обработка невозможности ходить
            while (!board.moveFigure(row, col, row1, col1)){
                System.out.println("Ошибка! Повторите ход фигуры!");
                System.out.print("Введите ход: ");
                inputLine = console.nextLine();
                coords = inputLine.split(" ");
                row = Integer.parseInt(coords[0]);
                col = Integer.parseInt(coords[1]);
                row1 = Integer.parseInt(coords[2]);
                col1 = Integer.parseInt(coords[3]);
            }

            if (board.shahMat()){
                if (board.getColorGame() == 'w'){
                    System.out.println("Шах и мат! Победа белых!");
                    break;
                }
                else{
                    System.out.println("Шах и мат! Победа черных!");
                    break;
                }
            }

            // Смена хода
            switch (board.getColorGame()){
                case 'w': board.setColorGame('b');break;
                case 'b': board.setColorGame('w');break;
            }
        }
    }
}
