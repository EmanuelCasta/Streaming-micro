FROM confluentinc/cp-kafka:latest

# Directorio para los certificados
RUN mkdir -p /etc/kafka/certs

# Generar certificados SSL sin passphrase
RUN openssl req -new -newkey rsa:4096 -days 365 -x509 -subj "/CN=kafka" -keyout /etc/kafka/certs/kafka.key -out /etc/kafka/certs/kafka.crt -nodes \
    && openssl pkcs12 -export -in /etc/kafka/certs/kafka.crt -inkey /etc/kafka/certs/kafka.key -out /etc/kafka/certs/kafka.p12 -name kafka -password pass:changeit \
    && keytool -importkeystore -destkeystore /etc/kafka/certs/kafka.keystore.jks -srckeystore /etc/kafka/certs/kafka.p12 -srcstoretype PKCS12 -srcstorepass changeit -alias kafka -storepass changeit \
    && keytool -keystore /etc/kafka/certs/kafka.truststore.jks -alias CARoot -import -file /etc/kafka/certs/kafka.crt -storepass changeit -noprompt
