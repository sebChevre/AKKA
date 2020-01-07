package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;

import akka.actor.typed.javadsl.Behaviors;

import java.util.Date;

public class SimplestExample {

    //Wrapper message
    static class Bonjour{
         public final String qui;
         public final Date quand;

         public  Bonjour(String qui){
             this.qui = qui;
             this.quand = new Date();
         }
    }

    final static Behavior<Bonjour> bonjourComportement =
            Behaviors.receive(Bonjour.class).onMessage(Bonjour.class,(message)->{
                System.out.println("Bonjour!: " + message.qui +" : " + message.quand);
               return Behaviors.same();
               //return null;
            }).build();

    final static Behavior<Bonjour> helloComportement =
            Behaviors.receive(Bonjour.class).onMessage(Bonjour.class,(message)->{
                System.out.println("Hello!: " + message.qui +" : " + message.quand);
                return Behaviors.same();
                //return null;
            }).build();


    public static void main(String[] args) {

        ActorSystem as = ActorSystem.create(bonjourComportement,"bonjourSystem");

        ActorRef<Bonjour> bonjourActorRef = as;
        ActorRef<Bonjour> helloActorRef = as.create(helloComportement,"helloSubSystem");

        bonjourActorRef.tell(new Bonjour("Seb"));
        helloActorRef.tell(new Bonjour("Seb"));
    }
}
