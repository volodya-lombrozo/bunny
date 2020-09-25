package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import static org.junit.Assert.*;

public class TypedWorkTest {


    @Test
    public void match() {
        LatchWork work = new LatchWork();
        String type = "type";
        TypedWork typedWork = new TypedWork(type, work);

        boolean isMatch = typedWork.match(type);
        boolean nonMatch = typedWork.match("other");

        assertTrue(isMatch);
        assertFalse(nonMatch);
    }

    @Test(timeout = 100)
    public void doIfMatch() throws RabbitException {
        LatchWork work = new LatchWork();
        String type = "type";
        TypedWork typedWork = new TypedWork(type, work);

        typedWork.doIfMatch(type, new Message.Fake());

        assertEquals(type, typedWork.type());
        work.awaitSuccess();
    }

}