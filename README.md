# ProxyServer

Project is the implementation work for the course Network Programming and 
Distributed Application (D7001D) at Lulea University of Technology.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
* Java 1.8 or higher
```

### Installing

Run the ProxyServer (inside proxyserver package). Currently the server is kept running on static 7999 port. It should be configurable (later on).

Then run the main application. It will come up with a GUI where user can provide URL of any site and later content of that site will be fetched through the proxy TCP server running on port 7999.


```
javac ProxyServerApp.java
java ProxyServer
```

```
javac Main.java
java Main
```

![alt text](https://image.ibb.co/gxSxde/show.png)


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
 
