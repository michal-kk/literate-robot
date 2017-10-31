# literate-robot
Spring Cloud Stream Thing

## Message queue setup

### RabbitMQ

Spring Cloud Stream uses `localhost:5672`, user: `guest`, password: `guest` by default.

If you need anything other set it up by:
```
spring.rabbitmq.addresses=192.168.99.100:5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

See [RabbitMQ Binder](https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/_rabbitmq_binder.html) documentation for other options.

### Kafka

Switch to `kafka` branch. If you need any other kafka or zookeper adress set them with:
```
spring.cloud.stream.kafka.binder.brokers=192.168.99.100
spring.cloud.stream.kafka.binder.zkNodes=192.168.99.100
```

See [Apache Kafka Binder](https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/_apache_kafka_binder.html) documentation.
