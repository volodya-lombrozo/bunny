package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.Type;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class TypedWorkTest {


    @Test(timeout = 100)
    public void doWorkTest() throws RabbitException {
        TypedWork typedWork = new TypedWork();
        LatchWork first = new LatchWork();
        LatchWork second = new LatchWork();
        typedWork.addWorkForMessageType("first", first);
        typedWork.addWorkForMessageType("second", second);

        typedWork.doWork(new RabbitMessage("first message", new Type("first")));
        typedWork.doWork(new RabbitMessage("second message", new Type("second")));

        first.awaitSuccess();
        second.awaitSuccess();
    }
}