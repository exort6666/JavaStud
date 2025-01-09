package Figures;
//Ладья
public class Rook extends Figure {
    // Конструктор родительского класса
    public Rook(String name, char color) { super(name, color); }

    // Метод перемещения, имеющий свойства родительского метода
    public boolean canMove(int row, int col, int row1, int col1) {
        for (int i = 0;i<8;i++) {
            // Горизонталь
            if ( (row == row1) && ( (col + i == col1) || (col - i == col1) ) ){
                this.isFirstStep = false;
                return true;
            }
            // Вертикаль
            if ( (col == col1) && ( (row + i == row1) || (row - i == row1) ) ){
                this.isFirstStep = false;
                return true;
            }
        }
        return false;
    }

    // Метод атаки, имеющий свойства родительского метода
    @Override
    public boolean canAttack(int row, int col, int row1, int col1) { return this.canMove(row, col, row1, col1); }
}
