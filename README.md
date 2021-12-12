zookeeper-server-start.bat ../../config/zookeeper.properties

kafka-server-start.bat ../../config/server.properties

connect-standalone.bat ../../config/connect-standalone.properties ../../config/connect-jdbc-source-client.properties ../../config/connect-jdbc-source-manager.properties ../../config/connect-jdbc-source-currency.properties ../../config/connect-jdbc-sink.properties

--------------------------------------------------------------

cd C:\kafka\bin\windows

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic resultstopic

kafka-console-producer.bat --broker-list localhost:9092 --topic results

Client:
{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"id"},{"type":"string","optional":false,"field":"name"},{"type":"float","optional":false,"field":"balance"},{"type":"int64","optional":false,"field":"manager_id"},{"type":"int64","optional":false,"field":"payment_total"},{"type":"int64","optional":false,"field":"credit_total"}],"optional":false},"payload":{"id":2,"name":"Client Name 2","balance":60.0,"manager_id":2,"payment_total":0,"credit_total":0}}

Manager:
{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"id"},{"type":"string","optional":false,"field":"name"},{"type":"float","optional":false,"field":"revenue"}],"optional":false},"payload":{"id":1,"name":"Manager Name 1","revenue":0.9}}

Currency:
{"schema":{"type":"struct","fields":[{"type":"string","optional":false,"field":"name"},{"type":"float","optional":false,"field":"to_euro"}],"optional":false},"payload":{"name":"EURO","to_euro":1.0}}

--------------------------------------------------------------

cd C:\Users\Nuno\Desktop\IS\Assignment 3\wildfly-26.0.0.Beta1\bin
standalone.bat