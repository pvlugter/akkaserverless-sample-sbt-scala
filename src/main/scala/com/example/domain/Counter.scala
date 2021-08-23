package com.example.domain

import com.akkaserverless.javasdk.Reply
import com.akkaserverless.javasdk.valueentity._
import com.example.counter_api._
import com.example.domain.counter_domain._
import com.google.protobuf.Empty

import scala.jdk.OptionConverters._

@ValueEntity(entityType = "counter")
class Counter {

  @CommandHandler
  def increase(increase: IncreaseValue, ctx: CommandContext[CounterState]): Reply[Empty] = {
    if (increase.value < 0) {
      Reply.failure("Increase requires a positive value. It was [" + increase.value + "].")
    } else {
      val state = ctx.getState.toScala.getOrElse(CounterState())
      ctx.updateState(state.withValue(state.value + increase.value))
      Reply.message(Empty.getDefaultInstance)
    }
  }

  @CommandHandler
  def decrease(decrease: DecreaseValue, ctx: CommandContext[CounterState]): Reply[Empty] = {
    if (decrease.value < 0) {
      Reply.failure("Decrease requires a positive value. It was [" + decrease.value + "].")
    } else {
      val state = ctx.getState.toScala.getOrElse(CounterState())
      ctx.updateState(state.withValue(state.value - decrease.value))
      Reply.message(Empty.getDefaultInstance)
    }
  }

  @CommandHandler
  def reset(_reset: ResetValue, ctx: CommandContext[CounterState]): Reply[Empty] = {
    ctx.updateState(CounterState())
    Reply.message(Empty.getDefaultInstance)
  }

  @CommandHandler
  def getCurrentCounter(_get: GetCounter, ctx: CommandContext[CounterState]): Reply[CurrentCounter] = {
    val value = ctx.getState.toScala.fold(0)(_.value)
    Reply.message(CurrentCounter(value))
  }
}
