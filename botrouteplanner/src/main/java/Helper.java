import com.sun.tools.jdeprscan.scan.Scan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Helper
{
    static int road = 0;
    static double cost = 0d;
    public String fileToString(File input) throws FileNotFoundException
    {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(input);

        while(scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
            if(scanner.hasNextLine())
                builder.append("\n");
        }

        return builder.toString();
    }

    public double calculateCost(Module a, Module b)
    {
        char typeA = a.getType();
        char typeB = b.getType();
        switch(typeA)
        {
            case 'S':
                return 2d;
            case 'B':
                if(typeB == 'S')
                    return 2d;
                else
                    return 1d;
            case 'H':
                if(typeB == 'S')
                    return 2d;
                else if(typeB == 'B')
                    return 1d;
                else
                    return 0.5d;
            default:
                return 0;
        }
    }
    public static String produceAnswer(Container optimal, boolean found)
    {
        StringBuilder answerBuilder = new StringBuilder();
        LinkedList<String> reverseTab = new LinkedList<>();
        Module in = optimal.getModule();
        cost += optimal.getCost();
        while(in.getLast() != null)
        {
            road++;
            StringBuilder builder = new StringBuilder();
            builder.append(in.getX()).append(" ").append(in.getY()).append("\n");
            reverseTab.add(builder.toString());
            in = in.getLast();
        }
        if(!found)
        {
            StringBuilder builder = new StringBuilder();
            builder.append(in.getX()).append(" ").append(in.getY()).append("\n");
            reverseTab.add(builder.toString());
        }
        while(!reverseTab.isEmpty())
        {
            answerBuilder.append(reverseTab.getLast());
            reverseTab.removeLast();
        }
        if(found)
        {
            String answerCost = String.format("%.1f", (float)cost);
            answerBuilder.append(answerCost).append("\n");
            answerBuilder.append(road).append("\n");
        }
        return answerBuilder.toString();
    }
    public String prepareAnswer(String answer)
    {
        Scanner scanner = new Scanner(answer);
        LinkedList<String> output = new LinkedList<>();
        while(scanner.hasNextLine())
        {
            output.add(scanner.nextLine());
        }
        StringBuilder builder = new StringBuilder();
        builder.append(output.getLast()).append("\n");
        output.removeLast();
        builder.append(output.getLast()).append("\n");
        output.removeLast();
        while(!output.isEmpty())
        {
            builder.append(output.getFirst()).append("\n");
            output.removeFirst();
        }
        return builder.toString();
    }

}
