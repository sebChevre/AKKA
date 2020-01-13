package com.ticketseller.routes

import akka.actor.{ActorRef, ActorSystem}
import akka.util.Timeout
import akka.pattern.ask
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.ticketseller.messages.Coachella._
import com.ticketseller.messages._

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import StatusCodes._

class RestApi(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit val requestTimeout: Timeout = timeout
  implicit def executionContext: ExecutionContextExecutor = system.dispatcher

  def createCoachella(): ActorRef = system.actorOf(Coachella.props)
}

trait RestRoutes extends CoachellaApi with EventMarshaller {
  val service = "show-tix"
  val version = "v1"

  //  endpoint for creating an event with tickets
  protected val createEventRoute: Route = {
    pathPrefix(service / version / "events" / Segment ) { event ⇒
      post {
        //    POST show-tix/v1/events/event_name
        entity(as[EventDescription]) { ed =>
          onSuccess(createEvent(event, ed.tickets)) {
            case Coachella.EventCreated(event) => complete(Created, event)
            case Coachella.EventExists =>
              val err = Error(s"$event event already exists!")
              complete(BadRequest, err)
          }
        }
      }
    }
  }


  val routes: Route = createEventRoute
}

trait CoachellaApi {

  def createCoachella(): ActorRef

  implicit def executionContext: ExecutionContext
  implicit def requestTimeout: Timeout

  lazy val coachella: ActorRef = createCoachella()

  def createEvent(event: String, numberOfTickets: Int): Future[EventResponse] = {
    coachella.ask(CreateEvent(event, numberOfTickets))
      .mapTo[EventResponse]
  }

  def getEvents(): Future[Events] = coachella.ask(GetEvents).mapTo[Events]

  def getEvent(event: String): Future[Option[Event]] = coachella.ask(GetEvent(event)).mapTo[Option[Event]]

  def cancelEvent(event: String): Future[Option[Event]] = coachella.ask(CancelEvent(event)).mapTo[Option[Event]]

  def requestTickets(event: String, tickets: Int): Future[TicketSeller.Tickets] = {
    coachella.ask(GetTickets(event, tickets)).mapTo[TicketSeller.Tickets]
  }
}
