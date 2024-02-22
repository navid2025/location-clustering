The project can be build by maven and following cammand:
- mvn clean package

After building the package, For running the Application you must use following command(you need java 17+):
- java -jar location-clustring-0.0.1-SNAPSHOT.jar
  
the application will be run at port 8080.

 You can use following URL of swagger to test the APIs:
- http://localhost:8080/swagger-ui/index.html

 #### This application secured by basic authentication.

There is a limitation for number of inputs, cause by OSRM service.
