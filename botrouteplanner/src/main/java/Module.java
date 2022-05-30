import com.sun.org.apache.xpath.internal.operations.Mod;

public class Module implements Comparable<Module>
{
    private int x;
    private int y;
    private double cost;
    private int visited; //0 1 2
    private char type;
    private Module last;
    public char getType()
    {
        return this.type;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public double getCost()
    {
        return this.cost;
    }
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    public int getVisited()
    {
        return this.visited;
    }
    public void setVisited(int visited)
    {
        this.visited = visited;
    }
    public void setLast(Module module)
    {
        this.last = module;
    }
    public Module getLast()
    {
        return this.last;
    }
    public Module(int x, int y, char type)
    {
        this.x = x;
        this.y = y;
        cost = Double.MAX_VALUE;
        visited = 0;
        this.type = type;
    }

    @Override
    public int compareTo(Module different)
    {
        return Double.compare(this.cost, different.getCost());
    }

}
