package Figures;
//Пешка
public class Pawn extends Figure {

    // Конструктор родительского класса
    public Pawn(String name, char color) { super(name, color); }

    // Метод перемещения, имеющий свойства родительского метода
    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        // Так как пешки не могут ходить назад
        // разделим 2 случая - за белых и черных
        if (this.isFirstStep){
            // За белых
            if ( ( (row + 2 == row1) || (row + 1 == row1) ) && (this.getColor() == 'w') && (col == col1) ){
                this.isFirstStep = false;
                return true;
            }
            // За черных
            if ( ( (row - 2 == row1) || (row - 1 == row1) ) && (this.getColor() == 'b') && (col == col1) ){
                this.isFirstStep = false;
                return true;
            }
        }else{
            // За белых
            if ( (row + 1 == row1) && (this.getColor() == 'w') && (col == col1) ){
                return true;
            }
            // За черных
            if ( (row - 1 == row1) && (this.getColor() == 'b') && (col == col1) ){
                return true;
            }
        }
        return false;
    }

    // Метод атаки, имеющий свойства родительского метода
    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        // Так как пешки не могут атакавать назад
        // разделим 2 случая - за белых и черных
        // За белых
        if ( (row1 - row) == 1 && Math.abs(col-col1) == 1 && (this.getColor() == 'w') ){
            return true;
        }
        // За черных
        if ( (row - row1) == 1 && Math.abs(col-col1) == 1 && (this.getColor() == 'b') ){
            return true;
        }
        return false;
    }
}
