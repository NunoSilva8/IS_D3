zookeeper-server-start.bat ../../config/zookeeper.properties

kafka-server-start.bat ../../config/server.properties

connect-standalone.bat ../../config/connect-standalone.properties ../../config/connect-jdbc-source-client.properties ../../config/connect-jdbc-source-manager.properties ../../config/connect-jdbc-source-currency.properties ../../config/connect-jdbc-sink.properties

--------------------------------------------------------------

cd C:\kafka\bin\windows

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic resultstopic

kafka-console-producer.bat --broker-list localhost:9092 --topic results

Client:
{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"id"},{"type":"string","optional":false,"field":"name"},{"type":"double","optional":false,"field":"balance"},{"type":"int64","optional":false,"field":"manager_id"},{"type":"double","optional":false,"field":"payment_total"},{"type":"double","optional":false,"field":"credit_total"},{"type":"int8","optional":false,"field":"payments_last_two_months"},{"type":"double","optional":false,"field":"balance_last_month"}],"optional":false},"payload":{"id":4,"name":"NOVO_CLIENTE","balance":0.0,"manager_id":6,"payment_total":0.0,"credit_total":0.0,"payments_last_two_months":0,"balance_last_month":0.0}}

{"balance":-68.76140000000001,"manager_id":6,"name":"NOVO_CLIENTE","balance_last_month":-68.76140000000001,"id":4,"payments_last_two_months":1,"payment_total":69.0284,"credit_total":0.267}


Manager:
{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"id"},{"type":"string","optional":false,"field":"name"},{"type":"double","optional":false,"field":"revenue"}],"optional":false},"payload":{"id":1,"name":"Manager Name 1","revenue":0.0}}

{"revenue":77.56,"name":"NOVO_MANAGER","id":6}


Currency:
{"schema":{"type":"struct","fields":[{"type":"string","optional":false,"field":"name"},{"type":"float","optional":false,"field":"to_euro"}],"optional":false},"payload":{"name":"EURO","to_euro":1.0}}

--------------------------------------------------------------

cd C:\Users\Nuno\Desktop\IS\Assignment 3\wildfly-26.0.0.Beta1\bin
standalone.bat