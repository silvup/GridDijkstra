public class Container
{
    private Module in;
    private int depth;
    private double cost;
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    public double getCost()
    {
        return this.cost;
    }
    public void setModule(Module in)
    {
        this.in = in;
    }
    public Module getModule()
    {
        return this.in;
    }
    public void setDepth(int depth)
    {
        this.depth = depth;
    }
    public int getDepth()
    {
        return this.depth;
    }
    public Container()
    {
        this.cost = Double.MAX_VALUE;
    }
}