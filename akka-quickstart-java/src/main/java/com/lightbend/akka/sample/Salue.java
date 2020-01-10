package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;

import java.util.Objects;

public final class Salue {

    public final String whom;
    public final ActorRef<Salutations> from;

    public Salue(String whom, ActorRef<Salutations> from) {
        this.whom = whom;
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salue greeted = (Salue) o;
        return Objects.equals(whom, greeted.whom) &&
                Objects.equals(from, greeted.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whom, from);
    }

    @Override
    public String toString() {
        return "Salue{" +
                "whom='" + whom + '\'' +
                ", from=" + from +
                '}';
    }
}
