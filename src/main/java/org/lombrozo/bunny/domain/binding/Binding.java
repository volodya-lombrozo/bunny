package org.lombrozo.bunny.domain.binding;


import org.lombrozo.bunny.domain.Declarable;
import org.lombrozo.bunny.message.Message;

public interface Binding extends Declarable {

    String source();

    String destination();

    String routingKey();

    class Fake implements Binding {

        @Override
        public void declare() {}

        @Override
        public String source() {
            return "";
        }

        @Override
        public String destination() {
            return "";
        }

        @Override
        public String routingKey() {
            return "";
        }

    }
}
