import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-02-04.
 */
public class MethodInvokerTest {
    @Test
    public void invoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var concatenated = (String) X.class.getDeclaredMethod("concat", String.class, int.class)
                .invoke(new X(),"a", 1);
        
        assertThat(concatenated, is("a1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invoke_wrongArguments() throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        X.class.getDeclaredMethod("concat", String.class, int.class).invoke("a", "b");
    }
}
