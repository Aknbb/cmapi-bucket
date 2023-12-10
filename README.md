# CMAPI Bucket

CMAPI Bucket is a  Spring Boot application designed to host, serve, update, and delete CMAPI messages through a REST API. The application is written in JDK 8 and utilizes Spring Boot version 2.4.2.

To get the most out of the CMAPI Bucket application, you can use [CMAPI Shouter](https://github.com/Aknbb/cmapi-shouter), an example client application that allows you to use your stored CMAPI messages.

### Features

1. **REST Endpoints:**
   - Allows users to add, delete, edit, and retrieve all CMAPI messages via REST.
   
2. **API Documentation:**
   - Provides comprehensive API documentation that describes the REST endpoints mentioned above.

3. **Message Storage:**
   - Stores CMAPI messages in the directory specified by the `CMAPI_DATA_DIR` environment variable. Upon startup, the application reads messages from this directory. If the directory is not defined, the application extracts the default data provided with the WAR file to the directory where the WAR file is located.

4. **System Information Endpoint:**
   - Includes a Systeminfo REST endpoint displaying information such as uptime, RAM consumption, CPU usage, application version, and the full address of the `CMAPI_DATA_DIR`. Authentication is required for this endpoint.

### Installation

1. **Clone the tile-layout-converter repository from GitHub:**

    ```
    git clone https://github.com/Aknbb/cmapi-bucket
    ```

2. **Set Environment Variable (Optional):** 

    Set the `CMAPI_DATA_DIR` environment variable to specify the directory for storing CMAPI messages.
    
    If the environment variable is not defined, the example data_dir folder under resources will be extracted in the application's working directory, and the application will read from this directory. You can access this environment variable through log records or the **systeminfo** REST endpoint.
    
3. **Change Admin Username & Password (Optional):** 

    It is recommended to change the admin username and password in the application.properties file. The default values are:
    ```
   security.admin.username=admin
   security.admin.password=changeit
    ```

4. **Use Maven to install dependencies and build the project:**

    ```
    mvn clean install
    ```
    This will generate the **cmapibucket-0.0.1** WAR file in the target directory.

5. Run the application locally using your favorite IDE or deploy it to the server by building the war file.

### API Documentation

Visit the API documentation to learn about the available REST endpoints and their usage.

Endpoint: `https://example-domain/index.html`

### System Information

To retrieve system information, use the following authenticated REST endpoint:

Endpoint: `https://example-domain/systeminfo`

Example Result:

```
{
  "application" : {
    "dataPath" : "C:\\cmapibucket\\data",
    "debugLevel" : "info"
  },
  "monitoring" : {
    "heapMemoryUsage" : "51,8 MB",
    "applicationCpuUsage" : "3,51 %",
    "systemCpuUsage" : "10,18 %",
    "upTime" : "50 min, 16 sec"
  },
  "build" : {
    "version" : "0.0.1",
    "timeStamp" : "04-12-2023 13:04"
  }
}
```

### License

This project is licensed under the [MIT License](LICENSE).

#### Contact
Feel free to explore my CMAPI Bucket project and get in touch if you have any questions or collaboration ideas. You can reach out to me via [akinbuyukbulut@gmail.com](mailto:akinbuyukbulut@gmail.com) or connect with me on [LinkedIn](https://www.linkedin.com/in/akinbuyukbulut/) and [GitHub](https://github.com/Aknbb).