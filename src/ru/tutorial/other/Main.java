package ru.tutorial.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.image.BufferedImage;

public class Main extends JFrame implements KeyListener, MouseInputListener {
    //Приписка static у переменных значит что это глобальная переменная
    //Приписка final у переменных значит что это неизменяемая константа, т.е. такие переменные нельзя именять
    //размер экрана, поменяйте на ваш
    static final int w = 1920;
    static final int h = 1080;
    //Сетка представляется в программе в виде двумерного массива, ячеек grid (в нашем случае в ячейках будут числа)
    static final int cellsX = (w - 200) / 20;//количество ячеек по оси х и у
    static final int cellsY = (h - 200) / 20;
    static final int[][] grid = new int[cellsX][cellsY];
    static {//заполняем сетку случайными часламми  от 0 до 17
        Random r = new Random();
        for (int i = 0; i < cellsX; i++) {
            for (int j = 0; j < cellsY; j++) {
                grid[i][j] = r.nextDouble() > 0.2 ? 0 : -1;
            }
        }
    }
    //мышка!!!
    static int mouseX = 0;
    static int mouseY = 0;
    static int buttonX = 100;
    static int buttonY = 0;
    static int buttonW = 400;
    static int buttonH = 100;
    static int buttonX1 = 700;
    static int buttonY1 = 100;
    static int buttonW1 = 400;
    static int buttonH1 = 100;
    //static boolean buttonActive = false;
    static boolean buttonActive = true;
    //давайте добавим игрока
    static int playerX = cellsX / 2;//положение игрока на сетке
    static int playerY = cellsY / 2;
    //Такое лучше делать через enum, но для простоты я использую числа
   static final int LAVA = -1;
    static final int GRASS = 0;//GRASS
    static final int gridX = 100;//координаты левого верхнего угла сетки
    static final int gridY = 100;
    static final int gridW = w - 200;//Шририна и высота сетки
    static final int gridH = h - 200;
    static final int cellSizeX = gridW / cellsX;// длинна стороны ячейки
    static final int cellSizeY = gridH / cellsY;
    //_________________________________________//

    //_________________________________________//

    static int frames = 0;
    static int frames1 = 0;
    static int frames2 = 0;
    static int frames3 = 0;
   static int frames4 = 0;
  static int frames5 = 0;
    static int kd = 0;
    static int kd1 = 0;
    static int kd2 = 0;
    static int kd3 = 0;
    static int kd4 = 0;
    static int p_hp=3;
    static int eres=0;
    static boolean p_hp_=true;
    static boolean sword_punch=false;
    static boolean egra=false;
    static boolean buttonActive1=true;
    static  boolean lose= false;
    static  boolean win= false;
    static boolean x1=true;
    static boolean x2=true;
    static boolean x3=true;
    static boolean x4=true;
    static boolean x5=true;
    static boolean x6=true;
    /////////////////////////////////////
   // static boolean z=true;
   // static boolean z_hp_=true;
   // static boolean z_hp_1=true;
  //  static int z_hp=5;
  //  static boolean z1=true;
   // static int z_hp1=5;
   // static int botX = cellsX / 2-20;//положение игрока на сетке
    //static int botY = cellsY / 2;
    //static int botTX = cellsX / 2;//положение зомби на сетке
    //static int botTY = cellsY / 2;
    //static int botX1 = cellsX / 2-10;//положение игрока на сетке
    //static int botY1 = cellsY / 2+5;
    //static int botTX1 = cellsX / 2;//положение зомби на сетке
    //static int botTY1 = cellsY / 2;
   static int[] z_hp = new int[2];
   static boolean[] z=new boolean[2];
   static boolean[] z_hp_=new boolean[2];
    static int[] zombieX = new int[2];
    static int[] zombieY = new int[2];
    static int[] targetX = new int[2];
    static int[] targetY = new int[2];
    public static void draw(Graphics2D g) {
        if(eres==0){
            lose= false;
            win= false;
            buttonActive1 = false;
            egra=true;
            buttonActive=true;
            p_hp=3;
            z_hp[0]=5;
            z_hp[1]=5;
            z[0]=true;
            z[1]=true;
            sword_punch=false;
            playerX = cellsX / 2;//положение игрока на сетке
            playerY = cellsY / 2;
            zombieX[0] = cellsX / 2-10;//положение игрока на сетке
            zombieY[0] = cellsY / 2-10;
            x1=true;
            x2=true;
            x3=true;
            x4=true;
            x5=true;
            x6=true;
            zombieX[1] = cellsX / 2+5;//положение игрока на сетке
            zombieY[1] = cellsY / 2-15;
            buttonActive = false;
            buttonActive1 = true;
            egra=false;
            sword_punch=false;
        }
        eres=10;
        if (buttonActive1) {
            lose= false;
            win= false;
            g.setColor(new Color(255, 101, 0, 255));
            g.fillRect(buttonX1, buttonY1, buttonW1, buttonH1);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3));
            g.drawRect(buttonX1, buttonY1, buttonW1, buttonH1);
            g.setColor(Color.BLACK);
            g.setFont(new Font("", Font.BOLD, buttonH1 / 4));
            g.drawString("PLAY", buttonX1 + buttonW1 / 5, buttonY1 + buttonH1 / 2);
        }
        if(z_hp[0]==0){
            z[0]=false;
        }
        if(z_hp[1]==0){
            z[1]=false;
        }
        if(z_hp[0]==0 && z_hp[1]==0  ){
            lose= false;
            win= true;
            egra=false;
        }
        if(p_hp==0){
            lose= true;
            win= false;
            egra=false;
        }
        if(win==true){
            lose= false;
            g.setColor(new Color(0, 255, 0, 255));
            g.drawString("you win", buttonX1 + buttonW1 / 5-100, buttonY1 + buttonH1 / 2-100);
            buttonActive1 = true;
        }
        if(lose==true){
            win= false;
            g.setColor(new Color(255, 0, 0, 255));
            g.drawString("you lose", buttonX1 + buttonW1 / 5-100, buttonY1 + buttonH1 / 2-100);
            buttonActive1 = true;
        }
        if (egra) {
            z_hp_[1] = false;
            z_hp_[0] = false;
            z_hp_[1] = true;
            z_hp_[0] = true;
            lose= false;
            win= false;
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3));
            g.drawRect(buttonX1 + buttonW1 / 5, buttonY1 + buttonH1 / 2-100,240,20);
            if(z[0]==true) {
                frames++;
                if (frames % 30 == 0) {
                    targetX[0] = playerX;
                    targetY[0] = playerY;
                    moveBot();
                    recalculatePaths(targetX[0],targetY[0]);
                }
                frames1++;
                if (frames1 % 30 == 0) {
                    kd2++;
                    if (kd2 == 2) {
                        turn_zombi_punch();
                        kd2 = 0;
                    }
                }
            }
            if(z[1]==true) {
                frames4++;
                if (frames4 % 30 == 0) {
                    targetX[1] = playerX;
                    targetY[1] = playerY;
                    moveBot1();
                    recalculatePaths1(targetX[1], targetY[1]);
                }
                frames5++;
                if (frames5 % 30 == 0) {
                    kd3++;
                    if (kd3 == 2) {
                        turn_zombi_punch1();
                        kd3 = 0;
                    }
                }
            }
                frames3++;
                if (frames3 % 60 == 0) {
                    kd1++;
                    if (kd1 == 20) {
                        if (p_hp <= 2) {
                            p_hp++;
                        }
                        kd1 = 0;
                    }
                }
                if (sword_punch == true) {
                    frames2++;
                    if (frames2 % 60 == 0) {
                        kd++;
                        if (kd == 1) {
                            x1 = true;
                        }
                        if (kd == 2) {
                            x2 = true;
                        }
                        if (kd == 3) {
                            x3 = true;
                        }
                        if (kd == 4) {
                            x4 = true;
                        }
                        if (kd == 5) {
                            x5 = true;
                        }
                        if (kd == 6) {
                            x6 = true;
                            frames2 = 0;
                            kd = 0;
                            sword_punch = false;
                        }
                    }
                }

            if(x1==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5, buttonY1 + buttonH1 / 2-100,40,20);
            }
            if(x2==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+40, buttonY1 + buttonH1 / 2-100,40,20);
            }
            if(x3==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+80, buttonY1 + buttonH1 / 2-100,40,20);
            }
            if(x4==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+120, buttonY1 + buttonH1 / 2-100,40,20);
            }
            if(x5==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+160, buttonY1 + buttonH1 / 2-100,40,20);
            }
            if(x6==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+200, buttonY1 + buttonH1 / 2-100,40,20);
            }
            if (p_hp_ == true) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("", Font.BOLD, 12));
                g.drawString(" Your HP:" + p_hp, 0, 100);
            }
            if (z_hp_[0] == true) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("", Font.BOLD, 12));
                g.drawString(" Zombi's HP:" + z_hp[0], 0, 200);
            }
            if (z_hp_[1] == true) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("", Font.BOLD, 12));
                g.drawString("1Zombi's HP:" + z_hp[1], 0, 300);
            }


            if (buttonActive) {
                g.setColor(new Color(255, 255, 0, 255));
                g.fillRect(buttonX, buttonY, buttonW, buttonH);
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(3));
                g.drawRect(buttonX, buttonY, buttonW, buttonH);
                g.setColor(Color.BLACK);
                g.setFont(new Font("", Font.BOLD, buttonH / 4));
                g.drawString("Go to MENU", buttonX + buttonW / 5, buttonY + buttonH / 2);
            }

            g.setFont(new Font("", Font.ITALIC, 10));
            g.drawString("Cheshire-Salmon", mouseX, mouseY);


            Random rx = new Random(cellsX);
            Random ry = new Random(cellsY);
            int x = rx.nextInt(cellsX);
            int y = ry.nextInt(cellsY);
            //чтобы нарисовать сетку нужно перебрать все её клетки
            for (int i = 0; i < cellsX; i++) {
                for (int j = 0; j < cellsY; j++) {
                    //и для каждой клетки нарисовать прямоугольник нужного цвета, проще всего это сделать через switch
                    switch (grid[i][j]) {
                        case LAVA:
                            g.setColor(new Color(252, 96, 9));
                            break;
                        case GRASS:
                            g.setColor(new Color(57, 228, 201));
                            break;
                        default:
                            //неправильное число в сетке
                    }
                   g.fillRect(gridX + cellSizeX * i, gridY + cellSizeY * j, cellSizeX, cellSizeY);
                    g.setColor(Color.BLACK);
                   if(i==x && j==y){
                     //  g.fillRect(x,y,20,20);
                   }
                }

            }

                //рисуем игрока поверх сетки
                g.setColor(new Color(0, 0, 0, 0.3f));//полупрозрачный круг-тень под игроком
                g.fillOval(gridX + cellSizeX * playerX, gridY + (cellSizeY) * playerY, cellSizeX, cellSizeY);
                g.setFont(new Font("", Font.BOLD, cellSizeY));
                g.setColor(Color.RED);
                // + cellSizeY смещение вниз, т.к. тест пишется из нижней левой точки, а клетка рисуется из верхней левой
                g.drawString("@", gridX + cellSizeX * playerX, gridY + cellSizeY * playerY + cellSizeY * 3 / 4);

            //рисуем зомби поверх сетки
            if(z[0]==true) {
                g.setColor(new Color(0, 0, 0, 0.3f));//полупрозрачный круг-тень под игроком
                g.fillOval(gridX + cellSizeX * zombieX[0], gridY + (cellSizeY) * zombieY[0], cellSizeX, cellSizeY);
                g.setFont(new Font("", Font.BOLD, cellSizeY));
                g.setColor(Color.CYAN);
                // + cellSizeY смещение вниз, т.к. тест пишется из нижней левой точки, а клетка рисуется из верхней левой
                g.drawString("@", gridX + cellSizeX * zombieX[0], gridY + cellSizeY * zombieY[0] + cellSizeY * 3 / 4);
            }
            if(z[1]==true) {
                g.setColor(new Color(0, 0, 0, 0.3f));//полупрозрачный круг-тень под игроком
                g.fillOval(gridX + cellSizeX * zombieX[1], gridY + (cellSizeY) * zombieY[1], cellSizeX, cellSizeY);
                g.setFont(new Font("", Font.BOLD, cellSizeY));
                g.setColor(Color.GREEN);
                // + cellSizeY смещение вниз, т.к. тест пишется из нижней левой точки, а клетка рисуется из верхней левой
                g.drawString("@", gridX + cellSizeX * zombieX[1], gridY + cellSizeY * zombieY[1] + cellSizeY * 3 / 4);
            }
        }
    }


    //функция делает один ход игрока в направлении dx dy
    public void turn(int dx, int dy) {
        if ( grid[playerX + dx][playerY + dy] !=LAVA ) {//Запрещаем ходить в воду и в лаву
                playerX += dx;
            playerY += dy;
        }
    }
    public void turn_sword() {
        if (z_hp[0]>=1) {
           if(sword_punch == false) {
               x1=false;
               x2=false;
               x3=false;
               x4=false;
               x5=false;
               x6=false;
                if (zombieX[0] == playerX && zombieY[0]  == playerY) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX - 1 && zombieY[0]  == playerY) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX && zombieY[0]  == playerY - 1) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX && zombieY[0]  == playerY + 1) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX + 1 && zombieY[0]  == playerY) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0]= false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX + 2 && zombieY[0]  == playerY) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (targetX[0] == playerX && targetY[0]  == playerY + 2) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX && zombieY[0]  == playerY - 2) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX - 2 && zombieY[0]  == playerY) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX + 1 && zombieY[0]  == playerY + 1) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX - 1 && zombieY[0]  == playerY + 1) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX + 1 && zombieY[0]  == playerY - 1) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                } else if (zombieX[0] == playerX - 1 && zombieY[0] == playerY - 1) {
                    z_hp[0] --;
                    p_hp_ = false;
                    z_hp_[0] = false;
                    p_hp_ = true;
                    z_hp_[0] = true;
                }
              sword_punch =true;
            }

        }
    }
    public void turn_sword1() {
        if (z_hp[1]>=1) {
            if(sword_punch == false) {
                x1=false;
                x2=false;
                x3=false;
                x4=false;
                x5=false;
                x6=false;
                if (zombieX[1] == playerX &&zombieY[1] == playerY) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX - 1 &&zombieY[1] == playerY) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX && zombieY[1] == playerY - 1) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX && zombieY[1] == playerY + 1) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX + 1 && zombieY[1] == playerY) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX + 2 && zombieY[1] == playerY) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX && zombieY[1] == playerY + 2) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX && zombieY[1] == playerY - 2) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX - 2 &&zombieY[1]== playerY) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX + 1 && zombieY[1] == playerY + 1) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX - 1 && zombieY[1] == playerY + 1) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                } else if (zombieX[1] == playerX + 1 && zombieY[1] == playerY - 1) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1]= true;
                } else if (zombieX[1] == playerX - 1 && zombieY[1] == playerY - 1) {
                    z_hp[1] --;
                    p_hp_ = false;
                    z_hp_[1] = false;
                    p_hp_ = true;
                    z_hp_[1] = true;
                }
                if(z[0]==false && z[1]==true) {
              sword_punch = true;
                }
            }

        }
    }

    public static void turn_zombi_punch() {
        if (p_hp>=1) {
            if (zombieX[0] == playerX && zombieY[0] == playerY) {
                p_hp=p_hp-1;
                p_hp_=false;
                z_hp_[0]=false;
                p_hp_=true;
                z_hp_[0]=true;
            }
        }
    }
    public static void turn_zombi_punch1() {
        if (p_hp>=1) {
            if (zombieX[1] == playerX && zombieY[1]== playerY) {
                p_hp=p_hp-1;
                p_hp_=false;
                z_hp_[1]=false;
                p_hp_=true;
                z_hp_[1]=true;
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
     //   System.out.println(e);
        System.out.println(playerX + " " + playerY);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                    turn(0, -1);
                    break;
            case KeyEvent.VK_S:
                    turn(0, 1);
                    break;
            case KeyEvent.VK_A:
                    turn(-1, 0);
                    break;
            case KeyEvent.VK_D:
                    turn(1, 0);
                    break;
            case KeyEvent.VK_SPACE:
                    turn_sword1();
                    turn_sword();
                    break;
            }
        }
    public static void recalculatePaths(int tx, int ty) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] >= 0) grid[i][j] = 0;
            }
        }
        grid[tx][ty] = 1;
        int cur = 1;

        boolean overwritten = false;
        do {
            overwritten = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != 0) continue;
                    if ((i - 1 >= 0 && grid[i - 1][j] == cur) ||
                            (j - 1 >= 0 && grid[i][j - 1] == cur) ||
                            (i + 1 < cellsX && grid[i + 1][j] == cur) ||
                            (j + 1 < cellsY && grid[i][j + 1] == cur)) {
                        grid[i][j] = cur + 1;
                        overwritten = true;
                    }
                }
            }
            cur++;
        } while (overwritten);

    }
    public static void recalculatePaths1(int tx1, int ty1) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] >= 0) grid[i][j] = 0;
            }
        }
        grid[tx1][ty1] = 1;
        int cur1 = 1;

        boolean overwritten1 = false;
        do {
            overwritten1 = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != 0) continue;
                    if ((i - 1 >= 0 && grid[i - 1][j] == cur1) ||
                            (j - 1 >= 0 && grid[i][j - 1] == cur1) ||
                            (i + 1 < cellsX && grid[i + 1][j] == cur1) ||
                            (j + 1 < cellsY && grid[i][j + 1] == cur1)) {
                        grid[i][j] = cur1 + 1;
                        overwritten1 = true;
                    }
                }
            }
            cur1++;
        } while (overwritten1);

    }
    public static void moveBot(){
        if(grid[zombieX[0] ][zombieY[0] ] > 0){
            if ((zombieX[0] - 1 >= 0 && grid[zombieX[0] - 1][zombieY[0] ] == grid[zombieX[0]][zombieY[0] ] -1) ){
                zombieX[0] --;
            } else if(zombieY[0]  - 1 >= 0 && grid[zombieX[0]][zombieY[0]  - 1]  == grid[zombieX[0]][zombieY[0] ] -1){
                zombieY[0] --;
            } else if((zombieX[0] + 1 < cellsX && grid[zombieX[0] + 1][zombieY[0] ]  == grid[zombieX[0]][zombieY[0] ] -1)) {
                zombieX[0]++;
            } else if     (zombieY[0]  + 1 < cellsY && grid[zombieX[0]][zombieY[0]  + 1]  == grid[zombieX[0]][zombieY[0] ] -1) {
                zombieY[0] ++;
            }
        }
    }
    public static void moveBot1(){
        if(grid[zombieX[1]][zombieY[1]] > 0){
            if ((zombieX[1] - 1 >= 0 && grid[zombieX[1] - 1][zombieY[1]] == grid[zombieX[1]][zombieY[1]] -1) ){
                zombieX[1]--;
            } else if(zombieY[1] - 1 >= 0 && grid[zombieX[1]][zombieY[1] - 1]  == grid[zombieX[1]][zombieY[1]] -1){
                zombieY[1]--;
            } else if((zombieX[1] + 1 < cellsX && grid[zombieX[1] + 1][zombieY[1]]  == grid[zombieX[1]][zombieY[1]] -1)) {
                zombieX[1]++;
            } else if     (zombieY[1] + 1 < cellsY && grid[zombieX[1]][zombieY[1] + 1]  == grid[zombieX[1]][zombieY[1]] -1) {
                zombieY[1]++;
            }
        }
    }
    //магический код позволяющий всему работать, лучше не трогать
    public static void main(String[] args) throws InterruptedException {
        Main jf = new Main();
        jf.setSize(w, h);//размер экрана
        jf.setUndecorated(false);//показать заголовок окна
        jf.setTitle("Обалденный код лосося");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.createBufferStrategy(2);
        jf.addKeyListener(jf);
        jf.addMouseListener(jf);//!!!!!!добавляем слушатель к окну
        jf.addMouseMotionListener(jf);///////!!!!!!!!

        //Создаем изображение своего курсора
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        java.awt.Graphics cursorGr = cursorImg.getGraphics();
        //рисуем
        cursorGr.setColor(new Color(23, 13, 214));
        cursorGr.fillOval(0, 0, 16, 16);
        cursorGr.setColor(Color.RED);
        cursorGr.drawLine(8, 0, 8, 16);
        cursorGr.drawLine(0, 8, 16, 8);
        //инициируем курсор
        Cursor myCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "custom cursor");
        // Set cursor to the JFrame.
        jf.getContentPane().setCursor(myCursor);
        //в бесконечном цикле рисуем новый кадр
        while (true) {
            long frameLength = 1000 / 120; //пытаемся работать из рассчета  60 кадров в секунду
            long start = System.currentTimeMillis();
            BufferStrategy bs = jf.getBufferStrategy();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, jf.getWidth(), jf.getHeight());
            draw(g);

            bs.show();
            g.dispose();

            long end = System.currentTimeMillis();
            long len = end - start;
            if (len < frameLength) {
                Thread.sleep(frameLength - len);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    ///////////////////////////////////////////////////////////////////////////
    ////                  Mouse interaction stuff                          ////
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
       if (e.getButton() == MouseEvent.BUTTON2) {//если мы нажали правую кнопку мыши
            try {
                //Перемещаем курсор мыши в случайную часть экрана
                Robot robot = new Robot();
                Random random = new Random();
                robot.mouseMove(random.nextInt(getWidth()), random.nextInt(getHeight()));
            } catch (AWTException ex) {
            }
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            //если мы нажали левую кнопку мыши, проверяем помали ли мы на кнопку
            if (buttonActive &&
                    e.getX() >= buttonX && e.getX() <= buttonX + buttonW &&
                    e.getY() >= buttonY && e.getY() <= buttonY + buttonH
            ) {
                buttonActive = false;
                buttonActive1 = true;
                egra=false;
                sword_punch=false;
            }
            if (buttonActive1 &&
                    e.getX() >= buttonX1 && e.getX() <= buttonX1 + buttonW1 &&
                    e.getY() >= buttonY1 && e.getY() <= buttonY1 + buttonH1
            ) {
                lose= false;
                win= false;
                buttonActive1 = false;
                egra=true;
                buttonActive=true;
                p_hp=3;
                z_hp[0]=5;
                z_hp[1]=5;
                z[0]=true;
                z[1]=true;
                sword_punch=false;
                playerX = cellsX / 2;//положение игрока на сетке
                playerY = cellsY / 2;
                zombieX[0] = cellsX / 2-10;//положение игрока на сетке
                zombieY[0] = cellsY / 2-10;
                x1=true;
                x2=true;
                x3=true;
                x4=true;
                x5=true;
                x6=true;
                zombieX[1] = cellsX / 2+5;//положение игрока на сетке
                zombieY[1] = cellsY / 2-15;
                /*
                lose= false;
                win= false;
                buttonActive1 = false;
                egra=true;
                buttonActive=true;
                p_hp=3;
                z_hp[0]=5;
                z_hp[1]=5;
                z[0]=true;
                z[1]=true;
                sword_punch=false;
                playerX = cellsX / 2;//положение игрока на сетке
                playerY = cellsY / 2;
                zombieX[0] = cellsX / 2-10;//положение игрока на сетке
                zombieY[0] = cellsY / 2-10;
                x1=true;
                x2=true;
                x3=true;
                x4=true;
                x5=true;
                x6=true;
                zombieX[1] = cellsX / 2+5;//положение игрока на сетке
                zombieY[1] = cellsY / 2-15;
                 buttonActive = false;
                buttonActive1 = true;
                egra=false;
                sword_punch=false;
                 */
            }
        }
        //если нажали на колесико, сделать кнопку активной
        if(e.getButton() == MouseEvent.BUTTON3 ){
         //   buttonActive = true;
        }



/*        int cx = (e.getX() - gridX) / cellSizeX;
        int cy = (e.getY() - gridY) / cellSizeY;
        if (e.getButton() == MouseEvent.BUTTON1) {
            grid[cx][cy] = -1;
            recalculatePaths(botTX, botTY);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            botX = playerX;
            botY = playerY;
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            botTX = cx;
            botTY = cy;
            recalculatePaths(botTX, botTY);
        }

 */
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}