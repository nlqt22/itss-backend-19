FROM postgres:15.3-alpine3.18
COPY init.sql /docker-entrypoint-initdb.d/