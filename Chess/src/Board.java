import Figures.*;
import java.util.Scanner;
public class Board {
    // Цвет игры - цвет стороны, которая первая ходит
    private char colorGame;

    // Геттер цвета игры
    public char getColorGame() { return colorGame; }

    // Сеттер цвета игры
    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    // Двумерный массив, хранящий ВСЕ фигуры поля
    private Figure fields[][] = new Figure[8][8];

    // Метод задающий изначальное расположение фигур
    public void start() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("kN", 'w'), new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'), new Knight("kN", 'w'), new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'),
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b')
        };
        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("kN", 'b'), new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'), new Knight("kN", 'b'), new Rook("R", 'b')
        };
    }

    // Геттер фигуры в определленной клетке
    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    // Cеттер фигуры в определленной клетке (ДЛЯ ТЕСТОВ)
    //public void setCell(int row, int col, Figure figure) {
    //    this.fields[row][col] = figure;
    //}

    // Метод вывода текущего расположения фигур
    public void printBoard() {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1; row--) {
            System.out.print(row);
            for (int col = 0; col < 8; col++) {
                System.out.print("|" + getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++) {
            System.out.print("    " + col);
        }
        System.out.println();
    }
    // Проверка наличия препятствий по пути к клетке (если есть возвращает true)
    // Для первой диагонали
    public boolean hasLetFirstDiagonal(int row, int col, int row1){
        int dist = 1;
        while ((row + dist != row1) && (row - dist != row1)) {
            if (row < row1) {
                if (this.fields[row + dist][col + dist] != null) { return true; }
            } else {
                if (this.fields[row - dist][col - dist] != null) { return true; }
            }
            dist += 1;
        }
        return false;
    }
    //Для второй диагонали
    public boolean hasLetSecondDiagonal(int row, int col, int row1){
        int dist = 1;
        while ( (row + dist != row1) && (row - dist != row1) ){
            if (row < row1) {
                if (this.fields[row+dist][col-dist] != null) { return true; }
            } else {
                if (this.fields[row-dist][col+dist] != null) { return true; }
            }
            dist += 1;
        }
        return false;
    }

    //Горизонталь
    public boolean hasLetHorizontal(int row, int col, int col1){
        int dist = 1;
        while ( (col + dist != col1) && (col - dist != col1)){
            if (col < col1){
                if (this.fields[row][col+dist] != null) { return true; }
            }
            else{
                if (this.fields[row][col-dist] != null) { return true; }
            }
            dist += 1;
        }
        return false;
    }

    //Вертикаль
    public boolean hasLetVertical(int row, int col, int row1){
        int dist = 1;
        while ( (row + dist != row1) && (row - dist != row1)){
            if (row < row1){
                if (this.fields[row+dist][col] != null) { return true; }
            }
            else{
                if (this.fields[row-dist][col] != null) { return true; }
            }
            dist += 1;
        }
        return false;
    }


    public boolean canProtectFirstDiagonal(int row, int col, int row1, char color){
        int dist = 1;
        while ( (row + dist != row1) && (row - dist != row1)){
            //Проверяю может ли какая либо фигура переместить для защиты короля
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if ( (this.fields[i][j] == null) || ( (this.fields[i][j].getColor()!=color) ) ){
                        if (row < row1){
                            if ( (this.fields[i][j].canMove(i,j,row+dist,col+dist) ) || (this.fields[i][j].canAttack(i,j,row+dist,col+dist) ) ){
                                if (hasLetFirstDiagonal(i,j,row+dist)){ return false; }
                                return true;
                            }
                        }
                        else{
                            if ( (this.fields[i][j].canMove(i,j,row-dist,col-dist) ) || (this.fields[i][j].canAttack(i,j,row+dist,col+dist) )){
                                if (hasLetFirstDiagonal(i,j,row-dist)){ return false; }
                                return true;
                            }
                        }
                    }
                }
            }
            dist+=1;
        }
        return false;
    }
    public boolean canProtectSecondDiagonal(int row, int col, int row1, char color){
        int dist = 1;
        while ( (row + dist != row1) && (row - dist != row1)){
            //Проверяю может ли какая либо фигура переместить для защиты короля
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if ( (this.fields[i][j]==null) || (this.fields[i][j].getColor()!=color)) {
                        if (row < row1){
                            if (this.fields[i][j].canMove(i,j,row+dist,col-dist)){
                                if (hasLetSecondDiagonal(i,j,row+dist)) {
                                    return false;
                                }
                                return true;
                            }
                        }
                        else{
                            if (this.fields[i][j].canMove(i,j,row-dist,col+dist)){
                                if (hasLetSecondDiagonal(i,j,row-dist)){ return false; }
                                return true;
                            }
                        }
                    }
                }
            }
            dist+=1;
        }
        return false;
    }
    public boolean canProtectVertical(int row, int col, int row1, char color){
        int dist = 1;
        while ((row + dist != row1) && (row - dist != row1)){
            //Проверяю может ли какая либо фигура переместить для защиты короля
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if ( (this.fields[i][j]==null) || (this.fields[i][j].getColor()!=color) ){
                        if (row < row1){
                            if (this.fields[i][j].canMove(i,j,row+dist,col)){
                                if (hasLetVertical(i,j,row+dist)){ return false; }
                                return true;
                            }
                        }
                        else{
                            if (this.fields[i][j].canMove(i,j,row-dist,col)){
                                if (hasLetVertical(i,j,row-dist)){ return false; }
                                return true;
                            }
                        }
                    }
                }
            }
            dist+=1;
        }
        return false;
    }
    public boolean canProtectHorizontal(int row, int col, int col1, char color){
        int dist = 1;
        while ( (col + dist != col1) && (col - dist != col1)){
            //Проверяю может ли какая либо фигура переместить для защиты короля
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if ( (this.fields[i][j]==null) || (this.fields[i][j].getColor()!=color) ){
                        if (col < col1){
                            if (this.fields[i][j].canMove(i,j,row,col+dist)){
                                if (hasLetHorizontal(i,j,col+dist)){ return false; }
                                return true;
                            }
                        }
                        else{
                            if (this.fields[i][j].canMove(i,j,row,col-dist)){
                                if (hasLetHorizontal(i,j,col-dist)){ return false; }
                                return true;
                            }
                        }
                    }
                }
            }
            dist+=1;
        }
        return false;
    }

    // Метод проверки клетки - может ли она быть атакована
    public boolean cellAttacked(int row, int col,char color) {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (this.fields[i][j]!=null){
                    if (color!=this.fields[i][j].getColor()){
                        if (this.fields[i][j].canAttack(i,j,row,col)){
                            // Уточнение по поводу препятствий
                            if ("kN".equals(this.fields[i][j].getName())){ return true; }

                            //Если король атакует клетку, но эта клетка для него не безопастна - не рассматриваем короля
                            if ("K".equals(this.fields[i][j].getName())){
                                Figure figure = this.fields[row][col];
                                this.fields[row][col] = this.fields[i][j];
                                if (cellAttacked(row,col,this.fields[i][j].getColor())){
                                    this.fields[row][col] = figure;
                                    continue;
                                }
                                this.fields[row][col] = figure;
                            }
                            for (int k1 = -7; k1 < 8; k1++){
                                // На первой диагонали
                                if ((i + k1 == row) && (j + k1 == col)) {
                                    if (hasLetFirstDiagonal(i, j, row)) { return false; }
                                    return true;
                                }

                                // На второй диагонали
                                if ( (i + k1 == row) && (j - k1 == col) ){
                                    if (hasLetSecondDiagonal(i, j, row)){ return false; }
                                    return true;
                                }

                                // Вертикаль
                                if ( (i + k1 == row) && (j == col) ){
                                    if (hasLetVertical(i, j, row)){ return false; }
                                    return true;
                                }

                                // Горизонталь
                                if ( (i == row) && (j + k1 == col) ){
                                    if (hasLetHorizontal(i, j, col)){ return false; }
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Метод на наличие шаха
    public Point shah(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (this.fields[i][j]!=null) {
                    if ("K".equals(this.fields[i][j].getName())) {
                        if (cellAttacked(i, j, fields[i][j].getColor())) {
                            Point point = new Point(i, j);
                            //Возвращает местоположение короля под угрозой
                            return point;
                        }
                    }
                }
            }
        }
        return null;
    }

    // Метод на наличие шаха и мата
    public boolean shahMat(){
        Point point = shah();
        if (point!=null){
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    // Проверка можно уничтожить угрозу другими фигурами или поставить какую либо фигуру на пути атаки
                    if ( (this.fields[i][j] != null) && (this.fields[i][j].canAttack (i, j, point.getX(), point.getY()) ) &&
                            (this.fields[point.getX()][point.getY()].getColor() != this.fields[i][j].getColor() ) ){
                        if (cellAttacked (i,j,fields[i][j].getColor()) ){ return false; }
                        if (canProtectFirstDiagonal(i, j, point.getX(), fields[i][j].getColor())) { return false; }
                        if (canProtectSecondDiagonal(i, j, point.getX(), fields[i][j].getColor())) { return false; }
                        if (canProtectVertical(i, j, point.getX(), fields[i][j].getColor())) { return false; }
                        if (canProtectHorizontal(i, j, point.getY(), fields[i][j].getColor())) { return false; }
                    }
                }
            }
            // Проверка можно ли сбежать королю
            for (int i = -1; i < 2;i++){
                for (int j = -1; j < 2; j++){
                    // Если хотя одна клетка вокруг короля не может быть атакована,то мата нет
                    if ( (point.getX() + i >= 0) && (point.getX() + i < 8) && (point.getY() + j >= 0) && (point.getY() + j < 8) ){
                        if ((!cellAttacked(point.getX() + i, point.getY() + j, this.fields[point.getX()][point.getY()].getColor()))
                                && (this.fields[point.getX() + i][point.getY() + j] == null)) {
                            return false;
                        }
                    }
                }
            }
            printBoard();
            System.out.println();
            return true;
        }
        return false;
    }
    public boolean moveFigure(int row, int col, int row1, int col1){
        Figure figure = this.fields[row][col];
        if (figure == null){
            return false;
        }
        Figure detShahFigure,pastFigure;

        // Проверяю условия роккеровки
        if (this.fields[row1][col1]!=null) {
            if (("R".equals(figure.getName())) && (figure.getIsFirstStep()) &&
                    ("K".equals(this.fields[row1][col1].getName())) && (this.fields[row1][col1].getIsFirstStep()) && (shah() == null)) {

                // Я решил учесть все возможные расстановки на данной доске

                // Для случая уникальной расстановки, где ладья и король стоят вместе на краю доски
                // В этом случае ладья может вытолкнуть короля за доску, что приведет к ошибке
                if (((row == 0) && (row1 == 1)) || ((row == 7) && (row1 == 6))) { return false; }
                if (((col == 0) && (col1 == 1)) || ((col == 7) && (col1 == 6))) { return false; }

                // Проверка препятствий на пути
                for (int i = 1; i < 8; i++) {
                    // Вертикаль
                    if ((Math.abs(row1 - row) == i) && (col == col1)) {
                        if (hasLetVertical(row, col, row1)) { return false; }
                    }

                    // Горизонталь
                    if ((row == row1) && (Math.abs(col1 - col) == i)) {
                        if (hasLetHorizontal(row, col, col1)) { return false; }
                    }
                }
                // Логика на случай расстановки ладьи и короля рядом
                if (row - row1 == 1) {
                    if (this.fields[row + 1][col] != null) { return false; }
                }
                if (row1 - row == 1) {
                    if (this.fields[row - 1][col] != null) { return false; }
                }
                if (col - col1 == 1) {
                    if (this.fields[row][col + 1] != null) { return false; }
                }
                if (col1 - col == 1) {
                    if (this.fields[row][col - 1] != null) { return false; }
                }

                // Роккеровка
                if (row == row1) {
                    if (col < col1) {
                        this.fields[row1][col1 - 1] = figure;
                        this.fields[row1][col1 - 2] = this.fields[row1][col1];
                    } else {
                        this.fields[row1][col1 + 1] = figure;
                        this.fields[row1][col1 + 2] = this.fields[row1][col1];
                    }
                    this.fields[row][col] = null;
                    this.fields[row1][col1] = null;
                    return true;
                }
                //Для ОСОБЫХ начальных расстановок
                if (col == col1) {
                    if (row < row1) {
                        this.fields[row1 - 1][col1] = figure;
                        this.fields[row1 - 2][col1] = this.fields[row1][col1];
                    } else {
                        this.fields[row1 + 1][col1] = figure;
                        this.fields[row1 + 2][col1] = this.fields[row1][col1];
                    }
                    this.fields[row][col] = null;
                    this.fields[row1][col1] = null;
                    return true;
                }
            }

            //Проверка на атаку короля (Короля можно атаковать только в случае РОККИРОВКИ)
            if ("K".equals(this.fields[row1][col1].getName())){
                return false;
            }
        }



        // Случай возможности перемещения БЕЗ атаки
        if ( ( (figure.canMove(row, col, row1, col1)) && (this.fields[row1][col1] == null && figure.getColor() == this.colorGame) )||
                // и С атакой
            ( (figure.canAttack(row, col, row1, col1) ) && (this.fields[row1][col1] != null) && (this.fields[row1][col1].getColor() != this.fields[row][col].getColor() ) ) )
        {
            if ( ("R".equals (figure.getName()) ) || ("P".equals (figure.getName()) ) ){
                for (int i = 1; i < 8; i++) {
                    // Вертикаль
                    if ((Math.abs(row1 - row) == i) && (col == col1)) {
                        if (hasLetVertical(row, col, row1)) { return false; }

                        if (shah()!=null){
                            detShahFigure = this.fields[row1][col1];
                            this.fields[row1][col1] = figure;
                            if (shah()!=null){
                                this.fields[row1][col1] = detShahFigure;
                                return false;
                            }
                        }
                        pastFigure = this.fields[row1][col1];
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        if ("P".equals (figure.getName()) ){
                            if ((row1 == 7)||(row1==0)){
                                System.out.println ("Введите какой фигурой станет пешка");
                                Scanner console = new Scanner(System.in);
                                String stroka = console.nextLine();
                                if ("Q".equals(stroka)) {
                                    this.fields[row1][col1] = new Queen(stroka, getColorGame());
                                }
                                else if ("R".equals(stroka)) {
                                    this.fields[row1][col1] = new Rook(stroka, getColorGame());
                                }
                                else if ("kN".equals(stroka)) {
                                    this.fields[row1][col1] = new Knight(stroka, getColorGame());
                                }
                                else if ("B".equals(stroka)) {
                                    this.fields[row1][col1] = new Bishop(stroka, getColorGame());
                                }
                                else if ("P".equals(stroka)) {
                                    this.fields[row1][col1] = new Pawn(stroka, getColorGame());
                                }
                                else {
                                    this.fields[row][col] = this.fields[row1][col1];
                                    this.fields[row1][col1] = pastFigure;
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                    // Горизонталь
                    if ((row == row1) && (Math.abs(col1 - col) == i)) {
                        if (hasLetHorizontal(row, col, col1)) { return false; }

                        if (shah()!=null){
                            detShahFigure = this.fields[row1][col1];
                            this.fields[row1][col1] = figure;
                            if (shah()!=null){
                                this.fields[row1][col1] = detShahFigure;
                                return false;
                            }
                        }
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        return true;
                    }
                }
            }

            if ( ("Q".equals (figure.getName()) ) || ("B".equals (figure.getName()) )  ){
                for (int i = -7; i < 8; i++) {
                    // Проверяю нет ли препятствий для прохода

                    // На первой диагонали
                    if ((row + i == row1) && (col + i == col1)) {
                        if (hasLetFirstDiagonal(row, col, row1)) { return false; }

                        if (shah()!=null){
                            detShahFigure = this.fields[row1][col1];
                            this.fields[row1][col1] = figure;
                            this.fields[row][col] = null;
                            if (shah()!=null){
                                this.fields[row1][col1] = detShahFigure;
                                return false;
                            }
                        }
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        return true;
                    }

                    // На второй диагонали
                    if ( (row + i == row1) && (col - i == col1) ){
                        if (hasLetSecondDiagonal(row, col, row1)){ return false; }

                        if (shah()!=null){
                            detShahFigure = this.fields[row1][col1];
                            this.fields[row1][col1] = figure;
                            if (shah()!=null){
                                this.fields[row1][col1] = detShahFigure;
                                return false;
                            }
                        }
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        return true;
                    }

                    // Вертикаль
                    if ( (row + i == row1) && (col == col1) ){
                        if (hasLetVertical(row, col, row1)){ return false; }

                        if (shah()!=null){
                            detShahFigure = this.fields[row1][col1];
                            this.fields[row1][col1] = figure;
                            if (shah()!=null){
                                this.fields[row1][col1] = detShahFigure;
                                return false;
                            }
                        }
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        return true;
                    }

                    // Горизонталь
                    if ( (row == row1) && (col + i == col1) ){
                        if (hasLetHorizontal(row, col, col1)){ return false; }

                        if (shah()!=null){
                            detShahFigure = this.fields[row1][col1];
                            this.fields[row1][col1] = figure;
                            if (shah()!=null){
                                this.fields[row1][col1] = detShahFigure;
                                return false;
                            }
                        }
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        return true;
                    }
                }
            }

            // Для короля
            if ("K".equals (figure.getName()) ){
                if (cellAttacked(row1,col1,figure.getColor())) { return false; }

                if (shah()!=null){
                    detShahFigure = this.fields[row1][col1];
                    this.fields[row1][col1] = figure;
                    this.fields[row][col] = null;
                    if (shah()!=null){
                        this.fields[row][col] = this.fields[row1][col1];
                        this.fields[row1][col1] = detShahFigure;
                        return false;
                    }
                }
                this.fields[row1][col1] = figure;
                this.fields[row][col] = null;
                return true;
            }
            // Для коня
            if (shah()!=null){
                detShahFigure = this.fields[row1][col1];
                this.fields[row1][col1] = figure;
                if (shah()!=null){
                    this.fields[row1][col1] = detShahFigure;
                    return false;
                }
            }
            this.fields[row1][col1] = figure;
            this.fields[row][col] = null;
            return true;
        }
        return false;
    }
}