package com.lightbend.akka.helloworld;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.lightbend.akka.helloworld.message.BonjourMessage;
import com.lightbend.akka.helloworld.message.StartMessage;

public class HelloWorldMain extends AbstractBehavior<StartMessage> {

    private final ActorRef<BonjourMessage> helloWorldActor;

    private HelloWorldMain(ActorContext<StartMessage> context) {
        super(context);
        helloWorldActor = getContext().spawn(HelloWorld.create(),"HelloWorlder");
    }

    public static Behavior<StartMessage> create() {
        return Behaviors.setup(HelloWorldMain::new);
    }


    @Override
    public Receive<StartMessage> createReceive() {
        return newReceiveBuilder().onMessage(StartMessage.class, (message) -> {
            getContext().getLog().info(message.nom);
            getContext().spawn(HelloWorld.create(), message.nom);

            ActorRef<BonjourMessage> replyTo =
                    getContext().spawn(HelloWorld.create(), message.nom + "1");

            helloWorldActor.tell(new BonjourMessage("Salut...", replyTo));
            return this;
        }).build();
    }
}
