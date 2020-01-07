package com.lightbend.akka.sample.firstactor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainFirsActor {

    private static ActorSystem system = null;

    public static void main(String[] args) {

        system = ActorSystem.create("test-system");
        ActorRef monPremierActeurRef = system.actorOf(Props.create(MonPremierActeur.class), "mon-premier-acteur");

        monPremierActeurRef.tell("yeppp",monPremierActeurRef);
    }

}
