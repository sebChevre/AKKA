package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.Objects;

// #greeter
public class Saluer extends AbstractBehavior<Salutations> {


  public static Behavior<Salutations> create() {
    return Behaviors.setup(Saluer::new);
  }

  private Saluer(ActorContext<Salutations> context) {
    super(context);
  }

  @Override
  public Receive<Salutations> createReceive() {
    return newReceiveBuilder().onMessage(Salutations.class, this::onGreet).build();
  }

  private Behavior<Salutations> onGreet(Salutations command) {
    getContext().getLog().info("Hello {}!", command.whom);
    //#greeter-send-message
    command.replyTo.tell(new Salue(command.whom, getContext().getSelf()));
    //#greeter-send-message
    return this;
  }
}
// #greeter

