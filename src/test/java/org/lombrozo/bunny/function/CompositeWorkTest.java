package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.properties.Type;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class CompositeWorkTest {


    @Test(timeout = 100)
    public void doWorkTest() throws RabbitException {
        CompositeWork compositeWork = new CompositeWork();
        LatchWork first = new LatchWork();
        LatchWork second = new LatchWork();
        compositeWork.addWorkForMessageType("first", first);
        compositeWork.addWorkForMessageType("second", second);

        compositeWork.doWork(new RabbitMessage("first message", new Type("first")));
        compositeWork.doWork(new RabbitMessage("second message", new Type("second")));

        first.awaitSuccess();
        second.awaitSuccess();
    }


    @Test(timeout = 100)
    public void doWorkTest_typedWorkInConstructor() throws RabbitException {
        LatchWork first = new LatchWork();
        CompositeWork compositeWork = new CompositeWork(new TypedWork("first", first));

        compositeWork.doWork(new RabbitMessage("first message", new Type("first")));

        first.awaitSuccess();
    }
}