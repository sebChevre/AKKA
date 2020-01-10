package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class GreeterMain extends AbstractBehavior<GreeterMain.Start> {

    public static class Start {
        public final String name;

        public Start(String name) {
            this.name = name;
        }
    }

    private final ActorRef<Salutations> greeter;

    public static Behavior<Start> create() {
        return Behaviors.setup(GreeterMain::new);
    }

    private GreeterMain(ActorContext<Start> context) {
        super(context);

        //#create-actors
        greeter = context.spawn(Saluer.create(), "greeter");
        //#create-actors
    }

    @Override
    public Receive<Start> createReceive() {

        return newReceiveBuilder().onMessage(Start.class, this::onStart).build();
    }

    private Behavior<Start> onStart(Start command) {
        //#create-actors
        ActorRef<Salue> replyTo =
                getContext().spawn(GreeterBot.create(3), command.name);
        greeter.tell(new Salutations(command.name, replyTo));
        //#create-actors
        return this;
    }
}
