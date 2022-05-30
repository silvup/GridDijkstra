import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest
{
    static PathFinder finder;
    static char[][] grid;
    static String[][][] products;
    static int startx, starty, stationx, stationy;
    static String productToFind;
    static int depth;
    static String output;

    @BeforeAll
    static void Setup()
    {
        finder = new PathFinder();
        startx = starty = 0;
        stationx = stationy = 1;
        productToFind = "P1";
        depth = 2;
        grid = new char[2][2];
        products = new String[2][2][2];
        products[1][0][0] = "P1";
        products[1][0][1] = "P1";
        grid[0][0] = 'S';
        grid[1][0] = 'B';
        grid[0][1] = 'H';
        grid[1][1] = 'H';
        output = finder.findPath(grid, products, startx, starty, stationx, stationy, productToFind, depth, false);
    }

    @Test
    void FindPath_ShouldHaveGoodLineNumber()
    {
        int lines = 0;
        Scanner scanner = new Scanner(output);
        while(scanner.hasNextLine())
        {
            scanner.nextLine();
            lines++;
        }
        assertEquals(5, lines);
    }
    @Test
    void FindPath_ShouldHaveGoodCost()
    {
        int lines = 0;
        Scanner scanner = new Scanner(output);
        String cost = new String();
        while(scanner.hasNextLine())
        {
            String tmp = scanner.nextLine();
            if(lines == 3)
                cost = tmp;
            lines++;
        }
        assertEquals("5,0", cost);
    }
    @Test
    void FindPath_ShouldHaveGoodRoute()
    {
        int lines = 0;
        Scanner scanner = new Scanner(output);
        StringBuilder route = new StringBuilder();
        while(scanner.hasNextLine())
        {
            String tmp = scanner.nextLine();
            if(lines < 3)
                route.append(tmp);
            lines++;
        }
        assertEquals("0 01 01 1", route.toString());
    }
    @Test
    void FindPath_ShouldHaveGoodRouteCount()
    {
        int lines = 0;
        Scanner scanner = new Scanner(output);
        String count = new String();
        while(scanner.hasNextLine())
        {
            String tmp = scanner.nextLine();
            if(lines == 4)
                count = tmp;
            lines++;
        }
        assertEquals("2", count);
    }

}