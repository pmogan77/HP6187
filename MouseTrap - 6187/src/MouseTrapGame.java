//Praveen Mogan

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Point;

public class MouseTrapGame
{
    public static final int MAX_WALLS = 6;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;

    private int mouseColumn;
    private int mouseRow;
    private int turnCount = 0;
    private char mouseDirection;
    private ArrayList<Point> walls = new ArrayList<Point>();
    Scanner keyboard = new Scanner(System.in);

    public MouseTrapGame ()
    {
        mouseDirection = '^';
        turnCount = 0;
        mouseRow = 4;
        mouseColumn = 4;
    }

    public void draw ()
    {
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int x= 0;x<=8;x++)
        {
            System.out.print(x+" ");

            for (int y = 0; y<=8;y++)
            {
                if (containsWall(x,y))
                    System.out.print("W ");

                else if (mouseRow==x&&mouseColumn==y&&(containsWall(x,y)==false))
                    System.out.print(mouseDirection+" ");

                else if (containsWall(x,y)==false&&(mouseRow==x && mouseColumn==y)==false)
                    System.out.print("- ");
                else
                    System.out.print("  ");
            }
            System.out.println("");

        }
    }

    public boolean containsWall (int x, int y)
    {
        Point bored = new Point(x,y);
        if (walls.contains(bored))
            return true;
        else
            return false;
    }

    public Point getDecayingWall()
    {
        if(walls.size()<=MAX_WALLS)
            return null;
        else
            return walls.get(6);
    }

    public void addWall()
    {
        System.out.println("Enter a row: ");
        int rowx = keyboard.nextInt();

        System.out.println("Enter a column: ");
        int coly = keyboard.nextInt();

        Point lol = new Point(rowx, coly);
        if (mouseRow==rowx && mouseColumn==coly)
            System.out.println("You can't place a wall on the mouse.");
        else if(containsWall(rowx, coly))
            System.out.println("You already have a wall there.");
        else
        {
            if (walls.size()==MAX_WALLS)
            {
                walls.remove(5);
            }
            walls.add(0,lol);
            turnCount++;
        }

    }

    public boolean trapped ()
    {
        if (walls.size()<=3||walls.isEmpty())
            return false;
        if (containsWall(mouseColumn,mouseRow-1)&&containsWall(mouseColumn+1,mouseRow)&&containsWall(mouseColumn-1,mouseRow)&&containsWall(mouseColumn,mouseRow+1))
            return true;
        else if (mouseColumn==0&&containsWall(mouseColumn+1,mouseRow)&&containsWall(mouseColumn,mouseRow+1)&&containsWall(mouseColumn,mouseRow-1))
            return true;
        else if (mouseColumn==8&&containsWall(mouseColumn-1,mouseRow)&&containsWall(mouseColumn,mouseRow+1)&&containsWall(mouseColumn,mouseRow-1))
            return true;
        else if (mouseRow==0&&containsWall(mouseColumn,mouseRow+1)&&containsWall(mouseColumn+1,mouseRow)&&containsWall(mouseColumn-1,mouseRow))
            return true;
        else if (mouseRow==8&&containsWall(mouseColumn,mouseRow-1)&&containsWall(mouseColumn+1,mouseRow)&&containsWall(mouseColumn-1,mouseRow))
            return true;
        else
            return false;
    }

    public boolean move()
    {
        if (trapped())
            return false;
        else
        {
            boolean moveUpTried = false;
            boolean moveLeftTried = false;
            boolean moveRightTried = false;
            boolean moveDownTried = false;
            for (;true;)
            {
                int x = (int)(Math.random()*9);

                if (trapped())
                    return false;

                if (moveUpTried&&moveLeftTried&&moveRightTried&&moveDownTried)
                    return false;
                if (moveUpTried&&moveLeftTried&&moveRightTried)
                    x=9;
                //System.out.println("Up: "+moveUpTried+ ", Left: "+moveLeftTried+", Right: "+moveRightTried+", Down: "+moveDownTried);

                //System.out.println("x:"+x+", mouseRow:"+mouseRow +", mouseColumn:"+mouseColumn + ", mouseDirection:" + mouseDirection  /*+", arraywall:" + walls*/);

                if (x>=0&&x<=4)
                {
                    moveUpTried = true;
                    if (mouseRow<=0)
                        continue;
                    else if (containsWall(mouseRow-1, mouseColumn))
                        continue;
                    else
                    {
                        mouseDirection = '^';
                        mouseRow--;
                        //System.out.println("Up: "+moveUpTried+ ", Left: "+moveLeftTried+", Right: "+moveRightTried+", Down: "+moveDownTried);
                        return true;
                    }
                }
                else if (x==5||x==6)
                {
                    moveRightTried = true;
                    if (mouseColumn>=8)
                        continue;
                    else if (containsWall(mouseRow, mouseColumn+1))
                        continue;
                    else
                    {
                        mouseDirection = '>';
                        mouseColumn++;
                        //System.out.println("Up: "+moveUpTried+ ", Left: "+moveLeftTried+", Right: "+moveRightTried+", Down: "+moveDownTried);
                        return true;
                    }
                }
                else if (x==7||x==8)
                {
                    moveLeftTried = true;
                    if (mouseColumn<=0)
                        continue;
                    else if (containsWall(mouseRow, mouseColumn-1))
                        continue;
                    else
                    {
                        mouseDirection = '<';
                        mouseColumn--;
                        //	System.out.println("Up: "+moveUpTried+ ", Left: "+moveLeftTried+", Right: "+moveRightTried+", Down: "+moveDownTried);
                        return true;
                    }
                }
                else if (x==9)
                {
                    moveDownTried = true;
                    if (mouseRow>=8)
                        continue;
                    else if (containsWall(mouseRow+1, mouseColumn))
                        continue;
                    else
                    {
                        mouseDirection = 'v';
                        mouseRow++;
                        //	System.out.println("Up: "+moveUpTried+ ", Left: "+moveLeftTried+", Right: "+moveRightTried+", Down: "+moveDownTried);
                        return true;
                    }
                }
                else
                    return false;
            }

        }
    }
    public int getTurnCount()
    {
        return turnCount;
    }

    public int getWallSize()
    {
        return walls.size();
    }

}