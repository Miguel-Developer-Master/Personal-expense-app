# The Project

This project is an expense tracker app, with it, you can add categories like:
"food", "transport" and any category you want.<br>
And then, add an expense like "today lunch" with today's date.<br>
The program has an interactive dashboard that shows how much you spent this month, how much you
already spent and how much expenses you have. <br>
It also has a complete CRUD for expenses and categories.

## Technical Part

It uses PostgreSQL for data, and uses Springboot for a full-stack application.
If you want to run the project, follow the next instructions.

# Running The Project

There are different ways to run project based on your OS (Operational System). 
Follow the instructions for yours.

## Dependencies

The program needs:
* A PostgreSQL environment
* Maven (preferentially any 3.8 version)
* Java (preferentially 21)


## Windows

### Java and Maven

This project was made on linux, then, for a better approach, I recommend you
- [this video]() to install and configure java
- and [this video](https://youtu.be/3EfvEZ_wThc) to install and configure maven 3.8

### PostgreSQL

this one is pretty bigger, then, take a water before make it.

#### Installing PostgreSQL App

First, go to [this page](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads),
once here, click in Windows x86-64 option of download, on the 18.6 version.<br>
Then wait the download finishes and download the app, if you have some trouble,
search for it in [YouTube](https://www.youtube.com).

#### Creating the DB

Ok, now you installed the app, search on Windows Search Bar for "pgAdmin 4".
Now expand "servers", "PostgreSQL 18", "Databases" then your main user.<br>
Now, right-click on your main user then select "Query Tool". Now, you can run SQL commands.<br>
Run:

```
CREATE ROLE LOGIN_ROLE
WITH LOGIN PASSWORD 'your_password';
```

And replace LOGIN_ROLE with the name you want and your_password with the password you want.<br>
Now, run:

```
CREATE DATABASE DB_NAME
OWNER LOGIN_ROLE;
```

Replace DB_NAME with your database name and LOGIN_ROLE with the previous login role you created.

#### Adding tables

Yet inside of Query Tool, run:
```
CREATE TABLE categories (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50)
);
```

and then:
```
CREATE TABLE expenses (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    amount NUMERIC,
    description VARCHAR(150),
    expense_data DATE NOT NULL DEFAULT CURRENT_DATE,
    category_id BIGINT REFERENCES categories(id) ON DELETE SET NULL
);
```

This is a simple and powerful step. it creates the table we'll need.

#### Adding everything to the application.properties

Now, inside of application.properties (src/main/resources) replace the camp after the "=" sign on the first line with:<br>
```
jdbc:postgresql://localhost:LOCAL_PORT/DB_NAME
```
replace LOCAL_PORT with your port, you didn't change it, just put 5432, and replace DB_NAME with your DataBase name.<br>
On the second line, replace the camp after the "=" sign with:
```
LOGIN_ROLE
```

Replacing with your login role name. Now, on the third line change the camp after the "=" with this:
```
YOUR_PASSWORD
```

And replace with your login role password.<br>
The project is ready, skip for the last step.

## Linux

### Java

First, check if tou have java installed. Run:

```
java --version
```

if you receive something like "java: command not found" or "openjdk xx.x.x.x" 
and xx be less than 21, do not skip this step.

Now, run:
```
sudo apt update
```

It'll ask your password, put it, and run. Now:
```
sudo apt install openjdk-21-jdk
```

Java is installed now, you can check it with:
```
java --version
```

### Maven

Now, let's run:
```
mvn --version
```

if it returns "maven: command not find" or the version is less than 3.8, do this: <br>

First, run:
```
sudo apt update
```

Now:
```
sudo apt install maven=3.8.7-2
```

Now, it's installed just as Java. You can check it with:
```
mvn --version
```

### PostgreSQL

#### Installing It

These steps are for you that doesn't have PostgreSQL installed yet, if you do, skip this one.<br>

Firstly, run:

```
sudo apt update
```

Once you did that, run:
```
sudo apt install postgresql
```

Now, it's installed. you can check it with:
```
psql --version
```

#### Configuring It

Run:
```
sudo systemctl start postgresql
```

To make sure PostgreSQL is running. Now, here start the real configuration.
Run:
```
sudo -u postgres psql
```

Now, you're inside the PostgreSQL terminal. Run:
```
CREATE ROLE login_role
WITH LOGIN PASSWORD 'your_actual_password';
```

Replace login_role for the name that you want to be the owner of Database.<br>
Replace your_actual_password with the password you want.<br>

Once you did that, run:
```
CREATE DATABASE db_name
OWNER login_role;
```

Replacing db_name with the database name you want,
and login_role with the login_role you created last command.

Then, just do:
```
\q
```
to go back to terminal.

The database is configured, now, you just need to test it connection and put the info inside the project.

Run:
```
psql -h localhost -U your_login_role -d db_name -W
```
Replacing your_login_role with your login role.
And your db_name with your db name.

it will ask the login_role password, and you have to put it correctly.<br>
Then, if you reach something like:
```
db_name=>
```
The connection is ok.

Now, the database is configured.

#### Putting Info Into Spring Boot

Now, go to application.properties (src/main/resources) and replace the camp "your_database_url" with:
```
jdbc:postgresql://localhost:YOUR_PORT/DB_NAME
```
Replacing YOUR_PORT for your PostgreSQL port (if you didn't change it, it's 5432),
and DB_NAME with your database name.

On the second line, replace the camp "your_database_username" with the login_role you set.<br>
On the third line, replace the camp "your_password" with the login_role password you set.

Save the file and go to the next step.


#### Creating Tables

Now, run:

```
psql -h localhost -U your_login_role -d db_name -W
```
Replacing your_login_role with your login role and db_name with your database name<br>
It will ask your password, answer it correctly.

Now, run:

```
CREATE TABLE categories (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50)
);
```

and then:
```
CREATE TABLE expenses (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    amount NUMERIC,
    description VARCHAR(150),
    expense_data DATE NOT NULL DEFAULT CURRENT_DATE,
    category_id BIGINT REFERENCES categories(id) ON DELETE SET NULL
);
```

Once you did that, you create the tables the project need.

## Finally, running (for both os)

Now, go to the projects root (it has src inside it) with "cd" command, and run these commands:

```
mvn clean package
```
And:
```
mvn spring-boot:run
```

And now, it's working! You can go to [localhost:8080](http://localhost:8080) and access the project!