package com.lightbend.akka.sample.firstactor;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;

public class MonPremierActeur extends AbstractActor {

    private static ActorSystem system = null;

    @Override
    public Receive createReceive() {
       return receiveBuilder().matchEquals("", p-> {
           System.out.println("The address of this actor is: " + getSelf());
       }).build();
    }
}
