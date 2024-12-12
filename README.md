
# Recipe Book Application

A full-stack web application for managing recipes, built with Spring Boot and Angular.

## 🚀 Features

- Create, read, update, and delete recipes
- Search recipes by name and meal type
- Filter recipes by meal categories (Breakfast, Lunch, Dinner)
- Material Design UI components with Angular Material
- Responsive layout with grid system
- OpenAPI/Swagger documentation
- RESTful API with Spring Boot
- Dockerized backend and frontend with Docker Compose for easy deployment

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.4.0**
- **Spring Data JPA** for database interaction
- **MySQL Database** for persistence
- **OpenAPI (Swagger)** for API documentation
- **Maven** for dependency management

### Frontend
- **Angular 19** for modern, reactive UI
- **Angular Material** for sleek UI components
- **TypeScript** for type-safe JavaScript
- **SCSS** for styling
- **Standalone Components** for modular and reusable code

## 🐳 Docker Setup

This project is fully Dockerized. You can use Docker Compose to set up and run both the backend and frontend services.

### Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### How to Run the Application

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd recipes-book
   ```

2. **Build and Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```
   This command will:
    - Build and run the Spring Boot backend (on `http://localhost:8080`)
    - Build and run the Angular frontend (on `http://localhost:4200`)

3. **Access the Application**
    - Open your browser and navigate to `http://localhost:4200` to interact with the frontend.

4. **Stop the Containers**  
   To stop the running containers, use:
   ```bash
   docker-compose down
   ```

## 🖥️ Development Setup

### Backend Setup
1. **Navigate to the Backend Directory**
   ```bash
   cd recipes-book
   ```

2. **Run the Application**
    - **Using Maven**:
      ```bash
      mvn spring-boot:run
      ```
    - **Using Java**:
      ```bash
      java -jar target/recipes-book-0.0.1-SNAPSHOT.jar
      ```

3. **Swagger Documentation**  
   Access the API documentation at `http://localhost:8080/swagger-ui.html`.

### Frontend Setup
1. **Navigate to the Frontend Directory**
   ```bash
   cd frontend
   ```

2. **Install Dependencies**
   ```bash
   npm install
   ```

3. **Run the Angular Development Server**
   ```bash
   ng serve
   ```
   Open `http://localhost:4200` in your browser to access the frontend.

## 📝 RESTful API Endpoints

### Create Recipe
- **POST** `/api/recipes`
- **Body**:
  ```json
  {
    "name": "Pancakes",
    "description": "Fluffy pancakes with syrup.",
    "timeRequired": 20,
    "mealType": "BREAKFAST",
    "ingredients": [
      { "name": "Flour", "amount": "1 cup" },
      { "name": "Milk", "amount": "1/2 cup" }
    ]
  }
  ```

### Get All Recipes
- **GET** `/api/recipes`

### Get Recipe by ID
- **GET** `/api/recipes/{id}`

### Search Recipes by Name or Meal Type
- **GET** `/api/recipes/search?name={name}`
- **GET** `/api/recipes/search?mealTypes={mealType}`  
  Example: `/api/recipes/search?mealTypes=BREAKFAST`

### Delete Recipe
- **DELETE** `/api/recipes/{id}`

## ✨ Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a pull request

## 📜 License

This project is licensed under the MIT License. See the LICENSE file for details.
