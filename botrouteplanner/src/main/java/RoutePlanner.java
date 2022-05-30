import java.util.Scanner;

public class RoutePlanner
{
    private char[][] grid;
    private String[][][] products;
    private int width, height;
    private int depth;
    private int startx, starty;
    private int stationx, stationy;
    private String productToFind;
    public int getWidth()
    {
        return this.width;
    }
    public int getHeight()
    {
        return this.height;
    }
    public int getDepth()
    {
        return this.depth;
    }
    public char[][] getGrid()
    {
        return this.grid;
    }
    public String[][][] getProducts()
    {
        return this.products;
    }
    public RoutePlanner(String gridInput)
    {
        int lines = 0;
        Scanner scanner = new Scanner(gridInput);
        if(scanner.hasNextLine())
        {
            lines++;
            String currentLine = scanner.nextLine();
            String[] split = currentLine.split(" ");
            if (split.length != 3)
                throw new IllegalArgumentException(currentLine);
            else
            {
                width = Integer.parseInt(split[0]);
                height = Integer.parseInt(split[1]);
                depth = Integer.parseInt(split[2]);
                grid = new char[width][height];
                products = new String[width][height][depth];
            }
        }
        for(int i = 0; i < height; i++)
        {
            String currentLine;
            if(scanner.hasNextLine())
                currentLine = scanner.nextLine();
            else
                throw new IllegalArgumentException("Wrong input!");
            for(int j = 0; j < width; j++)
            {
                grid[j][i] = currentLine.charAt(j);
            }
        }
        while(scanner.hasNextLine())
        {
            String currentLine = scanner.nextLine();
            String[] split = currentLine.split(" ");
            if(split.length != 4)
                throw new IllegalArgumentException("Wrong input!");
            else
            {
                int w = Integer.parseInt(split[1]);
                int h = Integer.parseInt(split[2]);
                int d = Integer.parseInt(split[3]);
                products[w][h][d] = new String(split[0]);
            }
        }
    }
    public String findRoute(String job)
    {
        //We will be using Dijkstra algorithm
        Scanner scanner = new Scanner(job);
        for(int i = 0; i < 3; i++)
        {
            String currentLine;
            if(scanner.hasNextLine())
                currentLine = scanner.nextLine();
            else
                throw new IllegalArgumentException("Wrong input!");
            String[] split;
            switch(i)
            {
                case 0:
                    split = currentLine.split(" ");
                    if(split.length != 2)
                        throw new IllegalArgumentException("Wrong input!");
                    else
                        this.startx = Integer.parseInt(split[0]);
                        this.starty = Integer.parseInt(split[1]);
                    break;
                case 1:
                    split = currentLine.split(" ");
                    if(split.length != 2)
                        throw new IllegalArgumentException("Wrong input!");
                    else
                        this.stationx = Integer.parseInt(split[0]);
                        this.stationy = Integer.parseInt(split[1]);
                    break;
                case 2:
                    this.productToFind = currentLine;
            }
        }
        PathFinder finder = new PathFinder();
        String output = finder.findPath(grid, products, startx, starty, stationx, stationy, productToFind, depth, false);
        Helper helper = new Helper();
        return helper.prepareAnswer(output);
    }
}
