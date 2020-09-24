package org.lombrozo.bunny.domain;


public interface Binding extends Declarable {


    class Fake implements Binding {

        @Override
        public void create() {

        }
    }
}
