# Web authentication examples
- [X509 Certificate](#x509-certificate-procedure)
  - Create root CA, server cert, client cert
  - Server cert and client cert and custom rootCA
  - Server cert only
  - Client cert only
  - Server cert is root. Client cert will be signed by the server cert (this will reduce chained validation).
- SAML IDP and SP `TODO`
  - Call SP first then redirect
  - Call SP first then redirect without need for input credentials
  - Call IDP first then call the SP
- JWT IDP and SP In Progress started 1130-2200 10/20/20
  - IDP
  - SP `TODO`
- Oauth Facebook, Google `TODO`
- AWS Cognito `TODO`
- Passwordless auth - using devices like USB, RFID cards etc. `TODO`

## X509 Certificate procedure
X509 Certificate procedure:
- There will be a root certificate which is to be trusted by all parties.
  - Create private key - `openssl genrsa -des3 -out rootCA.key 2048`
  - Generate self-signed cert - `openssl req -x509 -new -nodes -key rootCA.key -sha256 -days 3650 -out rootCA.crt`
- Create a CSR (certificate signing request) with openssl
  - Create private key - `openssl genrsa -out localhost.key 2048`
  - Generate CSR - `openssl req -new -key localhost.key -out localhost.csr`
  - Server can create its own
  - Client can create its own
- Sign the CSR with root certificate using openssl to generate certificate (.csr file)
  - `openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in client.csr -out client.crt -days 365 -Cacreateserial`
  - Use -extfile option for server SAN
- Package this in a keystore (PKCS12 or JKS)
  - `openssl pkcs12 -export -out client.p12 -inkey client.key -in client.crt`
  - For trust store - `keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file rootCA.crt -keystore truststore.jks`
