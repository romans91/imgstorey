# Imgstorey

Imgstorey is an image hosting service which allows the users to create galleries that may contain a combination of images and text blocks.
This service allows the users to create thoroughly described image galleries, instruction manuals, journals, or large bodies of text with a few interspersed images.

This application refers to each such gallery as a "storey", which may have one or many "elements". Each element may be an image or a text block.

# Running Instructions (Microsoft Windows)

## Backend

### Ensure that PostgreSQL is installed on your machine

If you do not have PostgreSQL installed on your machine, download and install it from the following link:

[Download PostgreSQL](https://www.postgresql.org/download/)

Verify that you have PostgreSQL installed on your machine by running the following command:

`psql -V`

### Create a PostgreSQL database for the application

Log into your PostgreSQL via the command prompt using the following command:

`psql -U [username]`

Execute the following command while logged into PostgreSQL to create the database that imgstorey will use to store its contents:

`CREATE DATABASE [database name];`

The port number that PostgreSQL uses is 5432 by default, but we may also verify it by executing the following command:

`SELECT setting FROM pg_settings WHERE name = 'port';`

### Optional: Connect IntelliJ IDEA to your database

If you have IntelliJ IDEA Ultimate, you can create a connection to this database via the Database Tool Window.
This will give you access to the database objects in the form of a user interface.

Create a new data source from the Database Tool Window, select "PostgreSQL" as the data source type, and input your PostgreSQL login details and port number into the form.

### Set PostgreSQL data source properties

Open "application.properties" found in "src/main/resources", and set the following properties:

`spring.datasource.url=jdbc:postgresql://localhost:[PostgreSQL port number]/[database name]`

`spring.datasource.username=[PostgreSQL username]`

`spring.datasource.password=[PostgreSQL password]`

## Amazon Web Services file storage configuration

### Creating an Amazon Web Services S3 bucket

Sign into the AWS console found in the following link:

[Amazon Web Services home page]{https://aws.amazon.com/}

Use the console to create an Amazon S3 bucket via "Services" -> "Storage" -> "S3". 
This bucket must be named "imgstorey-images".

[Click here for detailed instructions on S3 bucket creation]{https://docs.aws.amazon.com/AmazonS3/latest/userguide/create-bucket-overview.html}

### Using your Amazon Web Services S3 bucket's credentials in your application

First, ensure the AWS Region that your bucket belongs to matches the region specified in the "awsBucketRegion" variable of the "AwsConfig" class.
This class is found in "com.romans91.imgstorey.config" package in the "src/main/java" directory.

For example, an awsBucketRegion variable with a value of "Regions.AP_SOUTHEAST_2" corresponds to the AWS Region named "Asia Pacific (Sydney) ap-southeast-2".

Then, create a new access key and secret access key pair in the dashboard via "[your username]" -> "My Security Credentials" -> "Access Keys".
Creating a ser of credentials will automatically download a file named "rootkey.csv", which contains these credentials.
Place it into the root folder of this repository, and prepare to run the backend application!

### Run the application with IntelliJ IDEA

View the console to ensure the application has started. 
The information in the console should contain the port number, as well as the Hibernate queries that the application runs to set up the tables in our PostgreSQL data source.

## Frontend

### Ensure that Node.js is installed on your machine

If you do not have Node.js installed on your machine, download and install it from the following link:

[Download Node.js](https://nodejs.org/en/download/)

Verify that you have Node.js installed on your machine by running the following command:

`node -v`

### Running the frontend

Open the "src/main/frontend" folder using Visual Studio Code.

Run the following command:
`npm start`

Begin using the imgstorey application from http://localhost:3000/

## Using the application

### Create a new storey
To begin uploading images and text to imagestorey, we must first create a storey object that will contain these elements. 
Press the "Create New Storey" button on the front page to create and automatically navigate to the new storey.
The randomly generated ID for the storey will be visible in the URL.

### Append content to your storey
Use any of the forms in the editing panel at the bottom of the storey to populate it with images and/or text.
