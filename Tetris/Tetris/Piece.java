
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Piece {
    private int [][] m = new int[4][4]; //Creamos la pieza
    private Point pos; 
    private Color c; //Damos atributos al objeto
    public Piece(int shape){
        switch(shape){
            case(1):
            c = new Color(0, 255, 255);
            int[][] I= {{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}}; //Esta consecución de loops da la opción de moldear las piezas.
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = I[i][j];
                }
            }break;
            case(2):
            c = new Color(0, 0, 255);
            int[][] L= {{0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0}};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = L[i][j];
                }
            }break;
            case(3):
            c = new Color(255, 0, 0);
            int[][] J= {{0,0,1,0},{0,0,1,0},{0,1,1,0},{0,0,0,0}};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = J[i][j];
                }
            }break;
            case(4):
            c = new Color(0, 255, 0);
            int[][] O= {{0,0,0,0},{0,1,1,0},{0,1,1,0},{0,0,0,0}};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = O[i][j];
                }
            }break;
            case(5):
            c = new Color(255, 255, 153);
            int[][] T= {{0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0}};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = T[i][j];
                }
            }break;
            case(6):
            c = new Color(200, 0, 200);
            int[][] S= {{0,0,0,0},{0,0,1,1},{0,1,1,0},{0,0,0,0}};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = S[i][j];
                }
            }break;
            case(7):
            c = new Color(255, 255, 0);
            int[][] Z= {{0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0}};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    m[i][j] = Z[i][j];
                }
            }break;
        }
        pos = new Point(14,1);
    }

    public int[][] getMatrix(){ //Devuelve la matriz
        return m;
    }

    public int getPosX(){ //Devuelve la posición en el eje x
        return (int)pos.getX();
    }

    public int getPosY(){ //Devuelve la posición en el eje y
        return (int)pos.getY();
    }

    public Color getColor(){ //Devuelve el color del objeto
        return c;
    }

    public void setPositionToOrigin(){ //Mover a la posición de origen
        pos.move(5,0);
    }

    public void rotateRight(){ //Girar la pieza hacia la derecha, si no hay espacio gira hacia la izquierda cuando se da cuenta,ya que no puede hacerlo al instante
        int m2[][] = new int[4][4];
        for(int i = 0, j2 = 3; i < 4; i++, j2--){
            for(int j = 0, i2 = 0; j < 4; j++, i2++){
                m2[i2][j2] = m[i][j];
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                m[i][j] = m2[i][j];
            }
        }
    }

    public void rotateLeft(){ //Girar la pieza hacia la izquierda, si no hay espacio gira hacia la derecha cuando se da cuenta,ya que no puede hacerlo al instante
        int m2[][] = new int[4][4];
        for(int i = 0, j2 = 0; i < 4; i++, j2++){
            for(int j = 0, i2 = 3; j < 4; j++, i2--){
                m2[i2][j2] = m[i][j];
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                m[i][j] = m2[i][j];
            }
        }
    }

    public void moveDown(){ //Hacer avanzar la pieza hacia abajo en el tablero.
        pos.move((int)pos.getX(), (int)pos.getY() +1);
    }

    public void moveUp(){  //En el caso de colisionar vuelve a la posición anterior ya que el programa no puede reconocer la colisión al instante,entonces cuando detecta la colisión retrocede a una posición natural 
        pos.move((int)pos.getX(), (int)pos.getY() -1);
    }

    public void moveRight(){ //Hacer que la pieza se mueva hacia la derecha
        pos.move((int)pos.getX()+1, (int)pos.getY());
    }

    public void moveLeft(){ //Hacer que la pieza se mueva hacia la izquierda
        pos.move((int)pos.getX()-1, (int)pos.getY());
    }

    public void draw( Graphics g ){ //Pinta la pieza en la variable de tipo graphics g.
        g.setColor(c);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(m[i][j] == 1){
                    g.fillRect(((int)pos.getX()+j)*25, ((int)pos.getY()+i)*25, 24, 24);
                }
            }
        }

    }
}
