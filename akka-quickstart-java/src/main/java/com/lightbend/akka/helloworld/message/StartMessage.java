package com.lightbend.akka.helloworld.message;

public final class StartMessage {

    public final String nom;

    public StartMessage(String nom) {
        this.nom = nom;
    }
}
