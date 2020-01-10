package com.lightbend.akka.sample;

import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;
import org.junit.ClassRule;
import org.junit.Test;

//#definition
public class AkkaQuickstartTest {

    @ClassRule
    public static final TestKitJunitResource testKit = new TestKitJunitResource();
//#definition

    //#test
    @Test
    public void testGreeterActorSendingOfGreeting() {
        TestProbe<Salue> testProbe = testKit.createTestProbe();
        ActorRef<Salutations> underTest = testKit.spawn(Saluer.create(), "greeter");
        underTest.tell(new Salutations("Charles", testProbe.getRef()));
        testProbe.expectMessage(new Salue("Charles", underTest));
    }
    //#test
}
