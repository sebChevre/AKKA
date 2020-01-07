package com.lightbend.akka.sample.firstactor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.typed.javadsl.Behaviors;
import com.lightbend.akka.sample.Greeter;
import com.lightbend.akka.sample.SimplestExample;

public class MonPremierActeur extends AbstractActor {

    private static ActorSystem system = null;

    @Override
    public Receive createReceive() {
       return receiveBuilder().matchEquals("", p-> {
           System.out.println("The address of this actor is: " + getSelf());
       }).build();
    }
}
