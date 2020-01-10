package com.lightbend.akka.helloworld;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.lightbend.akka.helloworld.message.BonjourMessage;

//Acteur etend AbstractBehavior (typed)
public class HelloWorld extends AbstractBehavior<BonjourMessage> {

    private HelloWorld(ActorContext context) {
        super(context);
    }

    public static Behavior<BonjourMessage> create() {
        return Behaviors.setup(HelloWorld::new);
    }

    @Override
    public Receive createReceive() {
        return newReceiveBuilder().onMessage(BonjourMessage.class, (message) -> {
            getContext().getLog().info("Bonjour {}!", message.qui);
            message.deQui.tell(new BonjourMessage(message.qui, getContext().getSelf()));
            return this;
        }).build();
    }

}
