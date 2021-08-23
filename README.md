# Akka Serverless sample for Scala

Port of the simple Akka Serverless Counter example to Scala, with sbt, using the Java SDK.


## Running locally

Start the service:

```
sbt run
```

Start the Akka Serverless dev proxy:

```
docker run --rm -e USER_FUNCTION_HOST=host.docker.internal -p 9000:9000 gcr.io/akkaserverless-public/akkaserverless-proxy:0.7.0-beta.17
```

Use the counter service:

```
grpcurl -d '{ "counter_id": "abc123" }' -plaintext localhost:9000 com.example.CounterService/GetCurrentCounter

grpcurl -d '{ "counter_id": "abc123", "value": 123 }' -plaintext localhost:9000 com.example.CounterService/Increase

grpcurl -d '{ "counter_id": "abc123", "value": 81 }' -plaintext localhost:9000 com.example.CounterService/Decrease

grpcurl -d '{ "counter_id": "abc123" }' -plaintext localhost:9000 com.example.CounterService/GetCurrentCounter
```
