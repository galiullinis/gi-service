import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.mai.gi.service.tools.classifier.Verse;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

public class VerseTest {
    Verse test;

    @Before
    public void setUp(){
        try {
            test = new Verse();
        } catch (Exception e){
            fail("Text class creation failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown(){
        test.close();
    }

    @Test
    public void testClassify(){
        List<String> datas = new ArrayList<String>(){{
            add("36.0 19.0 5.0 0.4");
            add("35.0 17.0 26.0 0.1");
            add("114.0 11.0 40.0 0.12");
            add("5.0 9.0 2.0 0.0");
            add("5.0 20.0 2.0 0.0");
        }};

        for (String data : datas){
            if (test.classifyText(data) != "verse"){
                fail("Verse classify test failed!");
            }
        }
    }

}
