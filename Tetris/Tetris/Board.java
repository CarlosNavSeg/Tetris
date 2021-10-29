import java.awt.*;
public class Board{
    private Color[][] grid; //Creamos un objeto
    private final int cellSize = 25; //Tamaño de celda
    public Board(){
        grid = new Color[23][12];
        for(int i = 0; i<23; i++){ //Crea la pantalla vacía
            for(int j = 0; j<12; j++){
                grid[i][j] = new Color(0, 0, 0);
            }
        }
        for(int j = 0; j<12; j++){ //Vacía la fila de abajo
            grid[22][j] = new Color(128, 128, 128);
        }
        for(int j = 0; j<12; j= j + 11){ //Vacía las columnas
            for(int i = 0; i<23; i++){
                grid[i][j] = new Color(128, 128, 128);
            }
        }
    }

    private int indexOfFilledLine(int l){ //Detecta la posición de una fila llena
        int counter = 0;
        for(int j = 1; j<11; j++){
            if(grid[l][j].equals(new Color(0, 0, 0))){
                counter++;
            }
        }
        if(counter == 0){
            return l;
        }
        return -1;
    }

    private void deleteLine(int line){ //Borra la fila llena
        Color[][] grid2 = new Color[23][12];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                grid2[i][j] = grid[i][j];    
            }
        }
        for(int i = 0; i < line; i++){ 
            for(int j = 0; j < grid[0].length; j++){
                grid2[i+1][j] = grid[i][j];    
            }
        }
        for(int j = 1; j < grid[0].length-1; j++){
            grid2[0][j] = new Color(0, 0, 0);
        }
        grid = grid2;
    }

    public int deleteFilledLines(){ //Borra las filas llenas
        int index, counter=0, flag=0;
        do{
            flag = 0;
            for(int l = 21; l>0; l--){
                index = indexOfFilledLine(l);
                if(index != -1){
                    deleteLine(index);
                    counter++;
                    flag = 1;
                }
            }
        }while(flag==1);
        return counter;
    }

    public boolean checkForCollision(Piece P){ //Busca si hay colisión
        int[][] m = P.getMatrix();
        int x = P.getPosX(), y = P.getPosY();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(m[i][j] == 1){
                    if(!grid[i+y][j+x].equals(new Color(0, 0, 0))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void fixToGrid(Piece P){ //Fija la pieza a la tabla
        int x = P.getPosX(), y = P.getPosY();
        Color p = P.getColor();
        int[][] m = P.getMatrix();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(m[i][j]==1){
                    grid[i+y][j+x] = p;    
                }
            }
        }
    }

    public void draw( Graphics g ) { //Pinta la pieza en la variable de tipo g. 
        g.fillRect(0, 0, cellSize*grid[0].length, cellSize*grid.length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g.setColor(grid[i][j]);
                g.fillRect(cellSize*j, cellSize*i, cellSize-1, cellSize-1);
            }
        }          
    }
}