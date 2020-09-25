package integration;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.PrefixNameStrategy;
import org.lombrozo.bunny.domain.RabbitTopology;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.destination.ExchangeDestination;
import org.lombrozo.bunny.domain.exchange.DirectExchange;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.function.LatchWork;
import org.lombrozo.bunny.function.CompositeWork;
import org.lombrozo.bunny.host.RabbitHost;
import org.lombrozo.bunny.message.RabbitMessage;
import org.lombrozo.bunny.message.Type;
import org.lombrozo.bunny.util.exceptions.RabbitException;

@Ignore("For manual testing only")
public class CompositeWorkIntegrationTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = new RabbitHost("localhost", 5672, "ait", "ait")
                .connectionFactory(new PrefixNameStrategy("TypedTest"))
                .connect();
    }

    @Test(timeout = 250)
    public void sendThreeDifferentTypesOfMessages() throws RabbitException {
        String firstType = "first";
        String secondType = "second";
        String thirdType = "third";
        RabbitMessage firstMessage = new RabbitMessage("first message", new Type(firstType));
        RabbitMessage secondMessage = new RabbitMessage("second message", new Type(secondType));
        RabbitMessage thirdMessage = new RabbitMessage("third message", new Type(thirdType));
        DirectExchange exchange = new DirectExchange("TestExch", connection);
        NamedQueue queue = new NamedQueue("testQueue", connection);
        QueueBinding binding = new QueueBinding(exchange, queue, "#", connection);
        new RabbitTopology().register(exchange, queue, binding).createAll();
        CompositeWork compositeWork = new CompositeWork();
        LatchWork firstWork = new LatchWork();
        LatchWork secondWork = new LatchWork();
        LatchWork thirdWork = new LatchWork();
        compositeWork.addWorkForMessageType(firstType, firstWork);
        compositeWork.addWorkForMessageType(secondType, secondWork);
        compositeWork.addWorkForMessageType(thirdType, thirdWork);
        queue.subscribe(compositeWork);
        new ExchangeDestination(exchange, "#").send(firstMessage);
        exchange.send("#", secondMessage);
        exchange.send("#", thirdMessage);
        firstWork.awaitSuccess();
        secondWork.awaitSuccess();
        thirdWork.awaitSuccess();
    }
}
