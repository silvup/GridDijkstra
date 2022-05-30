import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoutePlannerTest
{
    private static RoutePlanner routePlanner;

    @BeforeAll
    static void Setup()
    {
        String input = new String("4 3 3\nHHSH\nHBHH\nHHOS\nP1 3 2 1\nP1 0 2 0\nP2 1 1 0");
        routePlanner = new RoutePlanner(input);
    }

    // testy poprawności organizacji danych

    @Test
    void GetWidth_ShouldPrintWidth()
    {
        int value = routePlanner.getWidth();
        assertEquals(4,value);
    }

    @Test
    void GetHeight_ShouldPrintHeight()
    {
        int value = routePlanner.getHeight();
        assertEquals(3,value);
    }

    @Test
    void GetDepth_ShouldPrintDepth()
    {
        int value = routePlanner.getDepth();
        assertEquals(3,value);
    }

    @Test
    void GetGrid_ShouldPrintElements()
    {
        String expected = new String("HHSHHBHHHHOS");
        StringBuilder actual = new StringBuilder();
        char[][] grid = routePlanner.getGrid();
        for(int i = 0; i < grid[0].length; i++)
        {
            for(int j = 0; j < grid.length; j++)
            {
                actual.append(grid[j][i]);
            }
        }
        assertEquals(expected,actual.toString());
    }

    @Test
    void GetProducts_ShouldFindElements()
    {
        int expected = 3;
        int actual = 0;
        String[][][] products = routePlanner.getProducts();
        for(int i = 0; i < products.length; i++)
        {
            for(int j = 0; j < products[0].length; j++)
            {
                for(int k = 0; k < products[0][0].length; k++)
                {
                    String current = products[i][j][k];
                    if(current != null)
                        actual++;
                }
            }
        }
        assertEquals(expected, actual);
    }
}