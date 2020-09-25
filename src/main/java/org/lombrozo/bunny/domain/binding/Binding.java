package org.lombrozo.bunny.domain.binding;


import org.lombrozo.bunny.domain.Declarable;

public interface Binding extends Declarable {

    String destination();

    String source();

    String routingKey();

    class Fake implements Binding {

        @Override
        public void declare() {

        }

        @Override
        public String destination() {
            return "";
        }

        @Override
        public String source() {
            return "";
        }

        @Override
        public String routingKey() {
            return "";
        }
    }
}
