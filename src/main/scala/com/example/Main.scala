package com.example

import com.akkaserverless.javasdk.AkkaServerless
import com.example.domain.Counter
import com.example.counter_api.CounterApiProto
import com.example.domain.counter_domain.CounterDomainProto
import org.slf4j.LoggerFactory

object Main extends App {
  val log = LoggerFactory.getLogger(Main.getClass)

  val service = new AkkaServerless()
    .registerValueEntity(
      classOf[Counter],
      CounterApiProto.javaDescriptor.findServiceByName("CounterService"),
      CounterDomainProto.javaDescriptor)

  log.info("Starting the Counter service")

  service.start.toCompletableFuture.get
}
