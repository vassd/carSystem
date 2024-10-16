<a name="readme-top"></a>
# The Car System App

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#running">Running</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

<a name="about-the-project"></a>

An application to simulate the car mechanism. 

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

<a name="built-with"></a>

* **Programming language:** Java 21
* **Framework:** Spring Boot
* **VCS:** Git
* **Build Tool:** Maven
* **Database:** PostgreSQL
* **Containerization:** Docker
* **Communication:** REST API
* **Linters:** PMD, SpotBugs, CheckStyle
* **Testing:** JUnit, Mockito, Jacoco
* **Open API:** Swagger

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

<a name="getting-started"></a>

## Prerequisites

<a name="prerequisites"></a>

* Java [21](https://www.oracle.com/java/technologies/downloads/#java21) 
* Docker

## Installation

<a name="installation"></a>

1. Install the packages
```sh
mvn clean install
```
2. Build docker image using dockerfile
```sh
docker build -t car-system-app .
```
3. Run docker-compose start up services
```sh
docker-compose.yml -p carsystem up -d
```

## Running

<a name="running"></a>

After the application starts you can call the API endpoints based on this [swagger documentation](http://localhost:8081/swagger).
For testing, you should create a new car with its components.
I recommend using the adminer page to add new records to the database.
Keep in mind, that first, you have to create the sub-components before adding a new car.
Running `mvn clean install` may not succeed for the first time due to the usage of `Math.Random` in the sub-systems logic. Just re-run the install in this case.

To check what is available in the database check [adminer](http://localhost:8080).
For adminer you can use these credentials to login:
* **System:** PostgreSQL
* **Server:** postgres
* **Username:** test
* **Password:** pass
* **Database:** mydb

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contact

<a name="contact"></a>

David Vass - [Linkedin](https://www.linkedin.com/in/d%C3%A1vid-vass-aa716b1b6/) - [email](mailto:nighturbex@protonmail.com)

<p align="right">(<a href="#readme-top">back to top</a>)</p>