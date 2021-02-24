//Praveen Mogan

import java.util.ArrayList;
import java.util.Scanner;

public class MainFile
{
    public static void main (String[] args)
    {
        System.out.println("Try to Trap the mouse by blocking the four directions it can move.");
        System.out.println("You can only have 6 walls up at once.");
        System.out.println("The program will ask you to place a wall every turn with row and col input");
        System.out.println("The wall will be marked as a 'W'");
        System.out.println("The mouse will be marked as a '^'");


        System.out.println("");

        MouseTrapGame game = new MouseTrapGame();

        while (game.trapped()==false)
        {
            game.draw();
            System.out.println("");
            game.addWall();
            if(!game.move())
                break;
            else
                continue;
        }
        game.draw();
        System.out.println("***Congratulations! You won in "+game.getTurnCount()+" turns!***");
    }
}