package org.lombrozo.bunny.util.subscription;

import com.rabbitmq.client.Channel;
import org.junit.Test;
import org.lombrozo.bunny.util.RandomString;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class RabbitConcurrentSubscriptionTest {


    @Test
    public void interruptTest_successful() throws RabbitException {
        RabbitConcurrentSubscription subscription = new RabbitConcurrentSubscription();
        List<Channel> channels = Stream.generate(this::mockChannel).limit(3).collect(Collectors.toList());
        for (Channel channel : channels) subscription.addChannel(channel, new RandomString().toString());

        subscription.interrupt();

        channels.forEach(this::assertMockChannel);
    }


    @Test(expected = RabbitException.class)
    public void interruptTest_interruptExceptionHappens() throws RabbitException {
        RabbitConcurrentSubscription subscription = new RabbitConcurrentSubscription();
        List<Channel> channels = Stream.generate(this::interruptedChannel).limit(3).collect(Collectors.toList());
        for (Channel channel : channels) subscription.addChannel(channel, new RandomString().toString());

        subscription.interrupt();

        fail();
    }


    @Test
    public void awaitTest_ignore() throws InterruptedException {
        List<Channel> channels = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        Subscription subscription = new RabbitConcurrentSubscription(channels, tags);

        subscription.await();

        assertTrue(channels.isEmpty());
        assertTrue(tags.isEmpty());
    }

    private Channel mockChannel() {
        return Mockito.mock(Channel.class);
    }

    private Channel interruptedChannel() {
        try {
            Channel mock = Mockito.mock(Channel.class);
            doThrow(new IOException("Some IO exception")).when(mock).basicCancel(anyString());
            return mock;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void assertMockChannel(Channel channel) {
        try {
            verify(channel, times(1)).basicCancel(anyString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}