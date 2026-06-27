## Luciano Tadeu Simões Simplicio Junior / RGA: 202421901044
## Pedro Inácio de Oliveira Silva / RGA: 202421901056

# 🏢 Access Control System (Condominium)

A complete and modern desktop system for access control, resident management, and entry/exit logging for a condominium. Developed focusing on good Object-Oriented Programming (OOP) practices for the Computer Engineering course at UFMT, this project consolidates concepts of client-server architecture, event-driven graphical interfaces, and data persistence.

## 🚀 Features and Highlights

- **Resident and Vehicle Management:** Comprehensive registration using a *Wizard* interface pattern (step-by-step) for dynamic vehicle allocation per unit.
- **Service Provider and Visitor Control:** Smart linking of service providers and visitors to the responsible resident.
- **Integrity Protection (Database):** Use of `ON DELETE CASCADE` constraints ensuring that the deletion of a resident automatically removes all their linked records, preventing orphan data.
- **Modern and Secure UI/UX:** Interface built natively with JavaFX, steering clear of standard OS visuals. Utilizes `TextFormatters` for real-time input validation and protection against `ConcurrentModificationException` when manipulating in-memory lists.

---

## 🛠️ Technologies Used

- **Language:** Java (Object-Oriented)
- **Graphical Interface:** JavaFX (Inline CSS styling and responsive layouts with VBox/HBox)
- **Database:** MySQL (Support for Local instances, Docker, or Cloud via Aiven)
- **Recommended IDE:** Visual Studio Code (VS Code)

---

## ⚙️ Prerequisites and Environment Setup

To run this project on your machine, you will need:
1. **Java Development Kit (JDK 17 or higher)**
2. **JavaFX SDK** (compatible with your JDK version)
3. **MySQL Connector/J** (JDBC Driver)
4. **db.properties:** create and configure the file with your database information.

### 🗄️ MySQL Table Scripts

Copy and paste the code below into your database to create the tables with the cascade rules:

```sql
CREATE DATABASE IF NOT EXISTS condominio;
USE condominio;

-- 1. Tabela de Moradores
CREATE TABLE moradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(50) NOT NULL
);

-- 2. Tabela de Veículos
CREATE TABLE veiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50),
    cor VARCHAR(30),
    morador_id INT NOT NULL,
    CONSTRAINT veiculos_ibfk_1 FOREIGN KEY (morador_id) REFERENCES moradores(id) ON DELETE CASCADE
);

-- 3. Tabela de Visitantes
CREATE TABLE visitantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    morador_id INT NOT NULL,
    CONSTRAINT visitantes_ibfk_1 FOREIGN KEY (morador_id) REFERENCES moradores(id) ON DELETE CASCADE
);

-- 4. Tabela de Funcionários
CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    funcao VARCHAR(50)
);

-- 5. Tabela de Prestadores de Serviço
CREATE TABLE prestadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    cnh VARCHAR(20),
    tiposervico VARCHAR(100),
    dataentrada DATETIME DEFAULT NULL,
    datasaida DATETIME DEFAULT NULL,
    morador_id INT NOT NULL,
    CONSTRAINT prestadores_ibfk_1 FOREIGN KEY (morador_id) REFERENCES moradores(id) ON DELETE CASCADE
);
```