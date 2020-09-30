package org.lombrozo.bunny.function;

import org.junit.Test;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.properties.Type;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import static org.junit.Assert.fail;


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


    @Test(timeout = 100)
    public void doWorkTest_addWorkByClass() throws RabbitException {
        LatchWork original = new LatchWork();
        CompositeWork compositeWork = new CompositeWork();
        Class<String> type = String.class;
        compositeWork.addWorkForMessageType(type, original);

        compositeWork.doWork(new RabbitMessage("first message", new Type(type)));

        original.awaitSuccess();
    }


    @Test(timeout = 100, expected = RabbitException.class)
    public void doWorkTest_emptyCorrelationId() throws RabbitException {
        CompositeWork compositeWork = new CompositeWork();

        compositeWork.doWork(new RabbitMessage("first message"));

        fail();
    }

}