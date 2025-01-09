package Figures;

//Король
public class King extends Figure {
    // Конструктор родительского класса
    public King(String name, char color) { super(name, color); }

    // Метод перемещения, имеющий свойства родительского метода
    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if ( (Math.abs(row - row1) <= 1) && (Math.abs(col - col1) <= 1) ){
            this.isFirstStep = false;
            return true;
        }
        return false;
    }

    // Метод атаки, имеющий свойства родительского метода
    @Override
    public boolean canAttack(int row, int col, int row1, int col1) { return this.canMove(row, col, row1, col1); }
}
