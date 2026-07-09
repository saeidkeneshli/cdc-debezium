# Read Me First
The following was discovered as part of building this project:
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/4.1.0/reference/messaging/kafka.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Debezium Connect

Use the following `docker-compose.yml` to start the Debezium Kafka Connect container.

### docker-compose.yml

```yaml
version: "2.5"

services:
  connect:
    image: debezium/connect:2.6
    container_name: debezium
    restart: always

    networks:
      - cdc-net

    ports:
      - "8083:8083"

    environment:
      BOOTSTRAP_SERVERS: kafka1:9092
      GROUP_ID: 1

      CONFIG_STORAGE_TOPIC: connect_configs
      OFFSET_STORAGE_TOPIC: connect_offsets
      STATUS_STORAGE_TOPIC: connect_statuses

      KEY_CONVERTER: org.apache.kafka.connect.json.JsonConverter
      VALUE_CONVERTER: org.apache.kafka.connect.json.JsonConverter

      KEY_CONVERTER_SCHEMAS_ENABLE: "false"
      VALUE_CONVERTER_SCHEMAS_ENABLE: "false"

networks:
  cdc-net:
    external: true
```

### Start the service

```bash
docker compose up -d
```

### Verify it's running

```bash
docker ps
```

### Access Kafka Connect

Open your browser or use curl:

```text
http://localhost:8083
```

or

```bash
curl http://localhost:8083
```

### Register the MySQL Connector

```bash
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "incoming-message-connector",
    "config": {
      "connector.class": "io.debezium.connector.mysql.MySqlConnector",
      "database.hostname": "mysql",
      "database.port": "3306",
      "database.user": "root",
      "database.password": "root",
      "database.server.id": "184054",
      "topic.prefix": "dbserver1",
      "database.include.list": "testdb",
      "table.include.list": "testdb.incoming_message",
      "include.schema.changes": "false",
      "snapshot.mode": "initial",
      "schema.history.internal.kafka.bootstrap.servers": "kafka1:9092",
      "schema.history.internal.kafka.topic": "schemahistory.testdb"
    }
  }'
```

### Check Connector Status

> **Note:** Your connector is created with the name `incoming-message-connector`, so use that name when checking its status.

```bash
curl http://localhost:8083/connectors/incoming-message-connector/status
```

### Delete the Connector

```bash
curl -X DELETE http://localhost:8083/connectors/incoming-message-connector
```

