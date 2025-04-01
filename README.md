# ShortLinks

A modern, lightweight URL shortening API built with Spring Boot.

## Features

- **URL Shortening**: Transform long URLs into compact, shareable links
- **URL Redirection**: Quick redirection from short URLs to original destinations
- **Usage Analytics**: Track how many times each shortened link has been accessed
- **RESTful API**: Simple, intuitive API for creating and managing short links

## Tech Stack

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- H2 Database (embedded)
- Maven
- Lombok

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository
```shell script
git clone https://github.com/sirutisb/ShortLinks.git
cd ShortLinks
```

2. Build the application
```shell script
mvn clean install
```

3. Run the application
```shell script
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Usage

### Shorten a URL

**Request:**
```shell script
curl -X POST -H "Content-Type: application/json" -d '{"url":"https://example.com/some/very/long/url/that/needs/shortening"}' http://localhost:8080/api/shorten
```

**Response:**
```json
{
  "originalUrl": "https://example.com/some/very/long/url/that/needs/shortening",
  "shortUrl": "http://localhost:8080/AbC123"
}
```

### Access a shortened URL

Simply visit the short URL in a browser, or use:

```shell script
curl -L http://localhost:8080/AbC123
```

The service will redirect to the original URL.

## Architecture

ShortLinks follows a traditional layered architecture:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic for URL shortening and retrieval
- **Repository Layer**: Manages data persistence
- **Entity Layer**: Defines the data model

Short codes are generated using a combination of uppercase letters, lowercase letters, and numbers to create a unique, compact identifier for each URL.
