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

See [the documentation](https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/_rabbitmq_binder.html) of other options.
