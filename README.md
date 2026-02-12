# 🚀 GitHub Repository Search API

A Spring Boot backend application that searches GitHub repositories using the GitHub API, stores results in PostgreSQL, and exposes REST APIs for querying and sorting repositories.

---

## 📌 Features

✅ Search repositories using GitHub API
✅ Store repositories in PostgreSQL database
✅ Sort by stars or forks
✅ Clean layered architecture (Controller → Service → Client → Repository)
✅ Environment variable based secrets (secure)
✅ Unit Testing with MockMvc
✅ Postman API Collection ready

---

## 🧱 Tech Stack

* Java 21
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* Maven
* GitHub REST API
* JUnit + MockMvc

---

## 📂 Project Structure

```
controller/
service/
repository/
entity/
dto/
githubClient/
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```
git clone https://github.com/anirudhasht/Github-Repo-Search.git
cd Github-Repo-Search
```

---

### 2️⃣ Configure Environment Variables

⚠️ This project uses environment variables for security.

#### Windows PowerShell

```
$env:DB_PASSWORD="your_db_password"
$env:GITHUB_TOKEN="your_github_token"
```

#### CMD

```
set DB_PASSWORD=your_db_password
set GITHUB_TOKEN=your_github_token
```

#### Mac/Linux

```
export DB_PASSWORD=your_db_password
export GITHUB_TOKEN=your_github_token
```

---

### 3️⃣ Configure PostgreSQL

Create database:

```
repo_db
```

Update if needed inside:

```
src/main/resources/application.properties
```

---

### 4️⃣ Run the Application

Using Maven:

```
mvn spring-boot:run
```

OR run from IDE:

```
Run GithubRepoSearchApplication.java
```

Server runs at:

```
http://localhost:8085
```

---

## 📡 API Endpoints

### 🔎 Search Repositories

**POST** `/api/github/search`

Example Body:

```
{
  "query": "DSA",
  "language": "Java",
  "sortBy": "stars"
}
```

Response:

```
{
  "status": "success",
  "count": 30,
  "data": [...]
}
```

---

### 📂 Get All Stored Repositories

**GET**

```
/api/github/repositories
```

Optional query param:

```
?sortBy=stars
?sortBy=forks
```

---

## 🧪 Running Tests

```
mvn test
```

---

## 🔐 Security Notes

* Secrets are NOT stored in the repository
* Uses environment variables:

  * `DB_PASSWORD`
  * `GITHUB_TOKEN`

---

## 📄 Postman Documentation

👉 https://documenter.getpostman.com/view/43458909/2sBXcAKixx

---

## 👨‍💻 Author

**Anirudh Ashrit**

GitHub:
[https://github.com/anirudhasht](https://github.com/anirudhasht)
