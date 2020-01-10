package com.lightbend.akka.helloworld.message;

import akka.actor.typed.ActorRef;

public final class BonjourMessage {

    public final String qui;
    public final ActorRef deQui;


    public BonjourMessage(String qui, ActorRef<BonjourMessage> deQui) {
        this.qui = qui;
        this.deQui = deQui;
    }
}
