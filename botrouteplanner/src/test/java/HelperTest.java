import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest
{
    static Helper helper;
    Module module1;
    Module module2;

    @BeforeAll
    static void Setup()
    {
        helper = new Helper();
    }

    @Test
    void CalculateCost_HS_ShouldPrint2()
    {
        module1 = new Module(0,0,'H');
        module2 = new Module(1,1,'S');
        double value = helper.calculateCost(module1, module2);
        assertEquals(2d, value);
    }
    @Test
    void CalculateCost_HB_ShouldPrint1()
    {
        module1 = new Module(0,0,'H');
        module2 = new Module(1,1,'B');
        double value = helper.calculateCost(module1, module2);
        assertEquals(1d, value);
    }
    @Test
    void CalculateCost_HH_ShouldPrint05()
    {
        module1 = new Module(0,0,'H');
        module2 = new Module(1,1,'H');
        double value = helper.calculateCost(module1, module2);
        assertEquals(0.5d, value);
    }

    @Test
    void PrepareAnswer_123_ShouldPrint321()
    {
        String value = "1\n2\n3\n";
        value = helper.prepareAnswer(value);
        assertEquals("3\n2\n1\n", value);
    }
}