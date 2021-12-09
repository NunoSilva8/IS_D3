zookeeper-server-start.bat ../../config/zookeeper.properties

kafka-server-start.bat ../../config/server.properties

connect-standalone.bat ../../config/connect-standalone.properties ../../config/connect-jdbc-source-clients.properties ../../config/connect-jdbc-source-managers.properties ../../config/connect-jdbc-sink.properties

--------------------------------------------------------------

cd C:\kafka\bin\windows

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic resultstopic

kafka-console-producer.bat --broker-list localhost:9092 --topic results

{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"a"},{"type":"int64","optional":true,"field":"b"},{"type":"int64","optional":true,"field":"c"}],"optional":false},"payload":{"a":1,"b":2,"c":3}}