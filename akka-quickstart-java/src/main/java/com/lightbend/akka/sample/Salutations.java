package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;

public final class Salutations {

    public final String whom;
    public final ActorRef<Salue> replyTo;

    public Salutations(String whom, ActorRef<Salue> replyTo) {
        this.whom = whom;
        this.replyTo = replyTo;
    }
}
