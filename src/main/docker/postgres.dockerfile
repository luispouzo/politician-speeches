FROM library/postgres

COPY ./db-initialization/1_schema.sql /docker-entrypoint-initdb.d
COPY ./db-initialization/2_data.sql /docker-entrypoint-initdb.d
