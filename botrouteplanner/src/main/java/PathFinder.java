import java.util.PriorityQueue;

public class PathFinder
{
    // I'll be using Dijkstra algorithm to find the shortest path to product;
    // H time: 0.5s ---> 3n + 4s
    // B time: 1s ---> 2n + 2s
    // S time: 2s ---> n + 1s
    // O ---> Out of order

    public String findPath(char[][] inputGrid, String[][][] products, int startx, int starty, int stationx, int stationy, String productToFind, int depth, boolean found)
    {
        Container optimal = new Container();
        Helper helper = new Helper();
        int width = inputGrid.length;
        int height = inputGrid[0].length;
        Module[][] grid = new Module[width][height];
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                Module tmp = new Module(i, j, inputGrid[i][j]);
                grid[i][j] = tmp;
            }
        }
        PriorityQueue<Module> Q = new PriorityQueue<>();
        Module start = grid[startx][starty];
        start.setCost(0d);
        Q.add(start);
        while(!Q.isEmpty())
        {
            Module current = Q.remove();
            if(current.getType() != 'O')
            {
                if(!found)
                {
                    for (int i = 0; i < depth; i++)
                    {
                        String currentContainer = products[current.getX()][current.getY()][i];
                        if (currentContainer != null && currentContainer.equals(productToFind))
                        {
                            double fullCost = current.getCost();
                            switch (current.getType())
                            {
                                case 'H':
                                    fullCost += 3d * (double) i + 4d;
                                    break;
                                case 'B':
                                    fullCost += 2d * (double) i + 2d;
                                    break;
                                case 'S':
                                    fullCost += (double) i + 1d;
                                    break;
                                default:
                                    throw new RuntimeException("This should never happen");
                            }
                            if (fullCost < optimal.getCost())
                            {
                                optimal.setModule(current);
                                optimal.setDepth(i);
                                optimal.setCost(fullCost);
                            }
                        }
                    }
                }
                else
                {
                    if(current.getX() == stationx && current.getY() == stationy)
                    {
                        optimal.setCost(current.getCost());
                        optimal.setModule(current);
                        optimal.setDepth(0);
                        break;
                    }
                }
            }
            current.setVisited(1); // seen
            int x = current.getX();
            int y = current.getY();
            int[] direction = {-1, 1};
            for(int i = 0; i < 2; i++)
            {
                Module next;
                if(y + direction[i] >= 0 && y + direction[i] < height)
                {
                    next = grid[x][y + direction[i]];
                    if (next.getVisited() != 2 && next.getType() != 'O')
                    {
                        double cost = helper.calculateCost(current, next);
                        if (cost + current.getCost() < next.getCost())
                        {
                            next.setCost(cost + current.getCost());
                            next.setLast(current);
                            if (next.getVisited() == 0)
                                Q.add(next);
                        }
                    }
                }
                if(x + direction[i] >= 0 && x + direction[i] < width)
                {
                    next = grid[x + direction[i]][y];
                    if (next.getVisited() != 2 && next.getType() != 'O')
                    {
                        double cost = helper.calculateCost(current, next);
                        if (cost + current.getCost() < next.getCost())
                        {
                            next.setCost(cost + current.getCost());
                            next.setLast(current);
                            if (next.getVisited() == 0)
                                Q.add(next);
                        }
                    }
                }
            }
            current.setVisited(2); // processed
        }
        if(found)
        {
            return helper.produceAnswer(optimal, true);
        }
        else
        {
            StringBuilder builder = new StringBuilder();
            builder.append(helper.produceAnswer(optimal, false));
            builder.append(findPath(inputGrid, products, optimal.getModule().getX(), optimal.getModule().getY(), stationx, stationy, productToFind, depth, true));
            return builder.toString();
        }
    }
}
