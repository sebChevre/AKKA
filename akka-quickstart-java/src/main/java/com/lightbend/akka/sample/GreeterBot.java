package com.lightbend.akka.sample;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class GreeterBot extends AbstractBehavior<Salue> {

    public static Behavior<Salue> create(int max) {
        return Behaviors.setup(context -> new GreeterBot(context, max));
    }

    private final int max;
    private int greetingCounter;

    private GreeterBot(ActorContext<Salue> context, int max) {
        super(context);
        this.max = max;
    }

    @Override
    public Receive<Salue> createReceive() {
        return newReceiveBuilder().onMessage(Salue.class, this::onGreeted).build();
    }

    private Behavior<Salue> onGreeted(Salue message) {
        greetingCounter++;
        getContext().getLog().info("Greeting {} for {}", greetingCounter, message.whom);
        if (greetingCounter == max) {
            return Behaviors.stopped();
        } else {
            message.from.tell(new Salutations(message.whom, getContext().getSelf()));
            return this;
        }
    }
}
