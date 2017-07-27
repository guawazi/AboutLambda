package cn.duozhuan.aboutlambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public  void testFor(){
        List<Double> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(Double.parseDouble(i+""));
        }

        int size = integerList.size();
        for (int i = 0; i < size; i++) {
            Double aDouble = integerList.get(i);
            if (aDouble % 2 == 0){
                integerList.remove(aDouble);
                i--;
                size--;
            }
        }

        for (Double integer : integerList) {
            
        }
        
    }

}