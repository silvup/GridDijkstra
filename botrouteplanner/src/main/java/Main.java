import java.io.File;
import java.io.FileNotFoundException;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        if(args.length != 2)
        {
            throw new IllegalArgumentException("Please provide names of two input files...");
        }
        else
        {
            Helper helper = new Helper();
            File gridInputFile = new File(args[0]);
            File jobInputFile = new File(args[1]);
            String gridInput = helper.fileToString(gridInputFile);
            String jobInput = helper.fileToString(jobInputFile);

            RoutePlanner planner = new RoutePlanner(gridInput);
            String output = planner.findRoute(jobInput);
            System.out.println(output);
        }

    }
}
