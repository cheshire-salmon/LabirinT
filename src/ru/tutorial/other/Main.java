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

    static int[] frames =new int[100];
    static int frames1 = 0;
    static int frames2 = 0;
    static int frames3 = 0;
    static int frames4 = 0;
    static int frames5 = 0;
     static int frames6 = 0;
    static int frames7 = 0;
    static int frames8 = 0;//
    static int kd = 0;
    static int b_kd = 0;
    static int kd1 = 0;
    static int kd2 = 0;
    static int kd3 = 0;
    static int kd4 = 0;//
    static int kd5 = 0;//
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
    static boolean b1=true;
    static boolean b2=true;
    static boolean b3=true;
    static boolean b4=true;
    static boolean b5=true;
    static boolean b6=true;
    static boolean selected_up=true;
    static boolean selected_down=false;
    static boolean selected_right=false;
    static boolean selected_left=false;
    static boolean selected_sword=true;
    static boolean selected_gun=false;
    static boolean gun_shooted=false;
    static boolean bullet=false;
    static boolean bullet_from_gun=false;
    static int bx=cellsX / 2;
    static int by=cellsY / 2;
    /////////////////////////////////////
   static int[] z_hp = new int[1000000];
   static boolean[] z=new boolean[1000000];
   static boolean[] z_hp_=new boolean[1000000];
    static int[] zombieX = new int[1000000];
    static int[] zombieY = new int[1000000];
    static int[] targetX = new int[1000000];
    static int[] targetY = new int[1000000];
    static int COUNT_ZOMBIE=1;
    public static void draw(Graphics2D g) {
        if(eres==0){
            bx=cellsX / 2;
            by=cellsY / 2;//  for (int i = 0; i < COUNT_ZOMBIE; i++) {}
            lose= false;
            win= false;
            buttonActive1 = false;
            egra=true;
            buttonActive=true;
            p_hp=3;
            for (int i = 0; i < COUNT_ZOMBIE; i++) {
                z_hp[i] = 3;
                z[i] = true;
            }
            sword_punch=false;
            playerX = cellsX / 2;//положение игрока на сетке
            playerY = cellsY / 2;
            x1=true;
            x2=true;
            x3=true;
            x4=true;
            x5=true;
            x6=true;
            b1=true;
            b2=true;
            b3=true;
            b4=true;
            b5=true;
            b6=true;
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
        for (int i = 0; i < COUNT_ZOMBIE; i++) {
            if(z_hp[i]==0){
                z[i]=false;
            }
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
            lose= false;
            win= false;
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3));
            g.drawRect(buttonX1 + buttonW1 / 5, buttonY1 + buttonH1 / 2-125,240,20);
            frames6++;
            if(frames6 % 1800 ==0){
                COUNT_ZOMBIE++;
                for (int i = 0; i < COUNT_ZOMBIE; i++) {
                    if(i==COUNT_ZOMBIE-1) {
                        z_hp[i] = 3;
                        z[i] = true;
                        zombieX[i] = cellsX / 2-10;
                        zombieY[i] = cellsY / 2-10;
                    }
                }
            }

            //  for (int i = 0; i < COUNT_ZOMBIE; i++) {}
            for (int i = 0; i < COUNT_ZOMBIE; i++) {
                if(z[i]==true) {
                    frames[i]++;
                    if (frames[i] % 30 == 0) {
                        targetX[i] = playerX;
                        targetY[i] = playerY;
                        moveBot(i);
                        recalculatePaths(targetX[i],targetY[i]);
                        turn_zombi_punch(i);
                    }
                }
            }
            //  for (int i = 0; i < COUNT_ZOMBIE; i++) {}
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
            //  for (int i = 0; i < COUNT_ZOMBIE; i++) {}
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
//  for (int i = 0; i < COUNT_ZOMBIE; i++) {}
            if(x1==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5, buttonY1 + buttonH1 / 2-125,40,20);
            }
            if(x2==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+40, buttonY1 + buttonH1 / 2-125,40,20);
            }
            if(x3==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+80, buttonY1 + buttonH1 / 2-125,40,20);
            }
            if(x4==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+120, buttonY1 + buttonH1 / 2-125,40,20);
            }
            if(x5==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+160, buttonY1 + buttonH1 / 2-125,40,20);
            }
            if(x6==true){
                g.setColor(new Color(0, 255, 0, 255));
                g.fillRect(buttonX1 + buttonW1 / 5+200, buttonY1 + buttonH1 / 2-125,40,20);
            }

            if (p_hp_ == true) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("", Font.BOLD, 12));
                g.drawString(" Your HP:" + p_hp, 0, 100);
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
            for (int i = 0; i < COUNT_ZOMBIE; i++) {
                if(z[i]==true) {
                    g.setColor(new Color(0, 0, 0, 0.3f));//полупрозрачный круг-тень под игроком
                    g.fillOval(gridX + cellSizeX * zombieX[i], gridY + (cellSizeY) * zombieY[i], cellSizeX, cellSizeY);
                    g.setFont(new Font("", Font.BOLD, cellSizeY));
                    g.setColor(Color.CYAN);
                    // + cellSizeY смещение вниз, т.к. тест пишется из нижней левой точки, а клетка рисуется из верхней левой
                    g.drawString("@", gridX + cellSizeX * zombieX[i], gridY + cellSizeY * zombieY[i] + cellSizeY * 3 / 4);
                }
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
    public void turn_sword(int zombieIndex) {
        if(selected_sword) {
            if (z_hp[zombieIndex] >= 1) {
                if (sword_punch == false) {
                    x1 = false;
                    x2 = false;
                    x3 = false;
                    x4 = false;
                    x5 = false;
                    x6 = false;
                    if (zombieX[zombieIndex] == playerX && zombieY[zombieIndex] == playerY) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX - 1 && zombieY[zombieIndex] == playerY) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX && zombieY[zombieIndex] == playerY - 1) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX && zombieY[zombieIndex] == playerY + 1) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX + 1 && zombieY[zombieIndex] == playerY) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX + 2 && zombieY[zombieIndex] == playerY) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (targetX[zombieIndex] == playerX && targetY[zombieIndex] == playerY + 2) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX && zombieY[zombieIndex] == playerY - 2) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX - 2 && zombieY[zombieIndex] == playerY) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX + 1 && zombieY[zombieIndex] == playerY + 1) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX - 1 && zombieY[zombieIndex] == playerY + 1) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX + 1 && zombieY[zombieIndex] == playerY - 1) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    } else if (zombieX[zombieIndex] == playerX - 1 && zombieY[zombieIndex] == playerY - 1) {
                        z_hp[zombieIndex]--;
                        p_hp_ = false;
                        z_hp_[zombieIndex] = false;
                        p_hp_ = true;
                        z_hp_[zombieIndex] = true;
                    }
                }
            }
        }
    }


    public static void turn_zombi_punch(int zombieIndex) {
        if (p_hp>=1) {
            if (zombieX[zombieIndex] == playerX && zombieY[zombieIndex] == playerY) {
                p_hp=p_hp-1;
                p_hp_=false;
                z_hp_[zombieIndex]=false;
                p_hp_=true;
                z_hp_[zombieIndex]=true;
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
                for (int i = 0; i < COUNT_ZOMBIE; i++) {
                    turn_sword(i);
                }
                sword_punch = true;
                    break;
            case KeyEvent.VK_UP:
                selected_up=true;
                selected_down=false;
                selected_right=false;
                selected_left=false;
                break;
            case KeyEvent.VK_DOWN:
                selected_up=false;
                selected_down=true;
                selected_right=false;
                selected_left=false;
                break;
            case KeyEvent.VK_RIGHT:
                selected_up=false;
                selected_down=false;
                selected_right=true;
                selected_left=false;
                break;
            case KeyEvent.VK_LEFT:
                selected_up=false;
                selected_down=false;
                selected_right=false;
                selected_left=true;
                break;
            case KeyEvent.VK_1:
               // selected_gun=false;
               // selected_sword =true;
                break;
            case KeyEvent.VK_2:
              //  selected_gun=true;
              //  selected_sword =false;
                break;

            }
        }
    public static void recalculatePaths(int tx, int ty) {/////////лишние методы...
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
    public static void moveBot(int zombieIndex){/////////лишние методы...
        if(grid[zombieX[zombieIndex] ][zombieY[zombieIndex] ] > 0){
            if ((zombieX[zombieIndex] - 1 >= 0 && grid[zombieX[zombieIndex] - 1][zombieY[zombieIndex] ] == grid[zombieX[zombieIndex]][zombieY[zombieIndex] ] -1) ){
                zombieX[zombieIndex] --;
            } else if(zombieY[zombieIndex]  - 1 >= 0 && grid[zombieX[zombieIndex]][zombieY[zombieIndex]  - 1]  == grid[zombieX[zombieIndex]][zombieY[zombieIndex] ] -1){
                zombieY[zombieIndex] --;
            } else if((zombieX[zombieIndex] + 1 < cellsX && grid[zombieX[zombieIndex] + 1][zombieY[zombieIndex] ]  == grid[zombieX[zombieIndex]][zombieY[zombieIndex] ] -1)) {
                zombieX[zombieIndex]++;
            } else if     (zombieY[zombieIndex]  + 1 < cellsY && grid[zombieX[zombieIndex]][zombieY[zombieIndex]  + 1]  == grid[zombieX[zombieIndex]][zombieY[zombieIndex] ] -1) {
                zombieY[zombieIndex] ++;
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
        //System.out.println(e);
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
                COUNT_ZOMBIE=1;
                lose= false;
                win= false;
                buttonActive1 = false;
                egra=true;
                buttonActive=true;
                p_hp=3;
                for (int i = 0; i < COUNT_ZOMBIE; i++) {
                    z_hp[i]=3;
                    z[i]=true;
                    zombieX[i] = cellsX / 2-10;//положение игрока на сетке
                    zombieY[i] = cellsY / 2-10;
                }
                sword_punch=false;
                playerX = cellsX / 2;//положение игрока на сетке
                playerY = cellsY / 2;
                x1=true;
                x2=true;
                x3=true;
                x4=true;
                x5=true;
                x6=true;
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