package Figures;

public abstract class Figure {
    private String name;
    private char color;
    protected boolean isFirstStep = true;

    // Конструктор класса-родителя

    public Figure(String name, char color){
        this.name = name;
        this.color = color;
    }

    // Геттер названия фигуры
    public String getName() {
        return name;
    }
    // Сеттер названия фигуры

    public void setName(String name) {
        this.name = name;
    }

    // Геттер цвета фигуры
    public char getColor() {
        return color;
    }
    // Сеттер цвета фигуры
    public void setColor(char color) {
        this.color = color;
    }

    // Геттер ходила ли фигура до этого хода
    public boolean getIsFirstStep (){
        return isFirstStep;
    }
    public void setIsFirstStep(boolean isFirstStep){
        this.isFirstStep = isFirstStep;
    }

    // Метод родителя, проверяющий может ли фигура ходить в заданную клетку
    // В родительском классе представляет проверку выхода за границу
    // и наличия движения (фигуре нельзя оставаться на том же месте)
    public boolean canMove(int row, int col, int row1, int col1){
        if ((row >= 0 && row < 8) && (col >= 0 && col < 8) && (row1>= 0 && row1 < 8)
                && (col1 >= 0 && col1 < 8) && (col != col1) && (row != row1)){
            return true;
        }
        return false;
    }

    // Метод родителя, проверяющий может ли фигура уничтожить другую фигуру

    public boolean canAttack(int row, int col, int row1, int col1){
        return this.canMove(row, col, row1, col1);
    }
}
