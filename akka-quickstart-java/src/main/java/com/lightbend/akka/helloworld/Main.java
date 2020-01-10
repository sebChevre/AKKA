package com.lightbend.akka.helloworld;

import akka.actor.typed.ActorSystem;
import com.lightbend.akka.helloworld.message.StartMessage;

public class Main {

    public static void main(String[] args) {

            ActorSystem system = ActorSystem.create(HelloWorldMain.create(), "hello");

            system.tell(new StartMessage("Seb1"));
            system.tell(new StartMessage("Seb2"));

    }
}
