FROM mysql

ENV MYSQL_ROOT_PASSWORD secretadmin
ENV MYSQL_DATABASE shuckle
ENV MYSQL_USER shuckle_admin
ENV MYSQL_PASSWORD shuckle_password

ADD src/main/resources/initDB.sql /docker-entrypoint-initdb.d/

EXPOSE 3306