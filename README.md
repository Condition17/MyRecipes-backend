# MyRecipes-backend
Spring boot api for an iOS app for cooking.

## Setup

* **Install docker**

* **Install maven**

* **Download docker image with Postgres database server**
```
docker pull postgres
```

* **Create data volume container**
```
docker create -v /var/lib/postgresql/data --name myrecipes_db postgres:9.4
```
This creates a new volume inside the myrecipes_db container of postgres:9.4 image at /var/lib/postgresql/data.

* **Create container with database instance**

```
docker run --volumes-from myrecipes_db --name myrecipes_pg -e POSTGRES_USER=${your db user } -e POSTGRES_PASSWORD=${your db user password} -d -P postgres:9.4

```
* **Connect to database container and create a database**

```
docker run -it --link myrecipes_pg:postgres --rm postgres sh -c 'exec psql -h "$POSTGRES_PORT_5432_TCP_ADDR" -p "$POSTGRES_PORT_5432_TCP_PORT" -U ${your db user} recipes'
```

* **Create hibernate.cfg.xml file**
Create "hibernate.cfg.xml" file in "src/main/respirces" directory. File content should have the following structure:

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">


<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.dialect">api.v1.AddOns.CustomPostgresDialect</property>
        <property name="hibernate.connection.url">jdbc:postgresql://<database_ip_address>:<database_port>/recipes?user=<your db user>&amp;password=<your db user password></property>
        <property name="connection_pool_size">1</property>
        <property name="show_sql">true</property>
        <mapping resource="Image.hbm.xml"></mapping>
        <mapping resource="Ingredient.hbm.xml"></mapping>
    </session-factory>
</hibernate-configuration>

```
You can see the database(instance) ip address running the following command:
```
docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' myrecipes_pg
```
* **Setup database structure**

```
docker exec myrecipes_pg /bin/sh -c 'psql -U {your db user} -d recipes </generatedb.sql'
```

* **Package application**

```
mvn package
```

* **Run server**
```
docker-compose up
```
After starting, the aplication this should be available on localhost, port 8080.

* **Optional**: Run src/main/java/api/v1/Utils/DbFiller main class to populate the newly created database.

