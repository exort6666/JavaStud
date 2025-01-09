package Figures;
//Конь
public class Knight extends Figure {
    // Конструктор родительского класса
    public Knight(String name, char color) { super(name, color); }

    // Метод перемещения, имеющий свойства родительского метода
    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        //Вверх
        if ( (row + 2 == row1) && (col + 1 == col1) ){ return true; }
        if ( (row + 2 == row1) && (col - 1 == col1) ){ return true; }
        //Вниз
        if ( (row - 2 == row1) && (col + 1 == col1) ){ return true; }
        if ( (row - 2 == row1) && (col - 1 == col1) ){ return true; }
        //Вправо
        if ( (col + 2 == col1) && (row + 1 == row1) ){ return true; }
        if ( (col + 2 == col1) && (row - 1 == row1) ){ return true; }
        //Влево
        if ( (col - 2 == col1) && (row + 1 == row1) ){ return true; }
        if ( (col - 2 == col1) && (row - 1 == row1) ){ return true; }

        return false;
    }

    // Метод атаки, имеющий свойства родительского метода
    @Override
    public boolean canAttack(int row, int col, int row1, int col1) { return this.canMove(row, col, row1, col1); }

}
