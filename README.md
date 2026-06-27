## Luciano Tadeu Simões Simplicio Junior / RGA: 202421901044
## Pedro Inácio de Oliveira Silva / RGA: 202421901056

# 🏢 Sistema de Controle de Acesso (Condomínio)

Um sistema desktop completo e moderno para controle de acesso, gestão de moradores e registro de entradas e saídas de um condomínio. Desenvolvido com foco em boas práticas de Programação Orientada a Objetos (POO) para o curso de Engenharia de Computação da UFMT, este projeto consolida conceitos de arquitetura cliente-servidor, interfaces gráficas orientadas a eventos e persistência de dados.

## 🚀 Funcionalidades e Diferenciais

- **Gestão de Moradores e Veículos:** Cadastro completo utilizando o padrão de interface *Wizard* (passo a passo) para alocação dinâmica de veículos por unidade.
- **Controle de Prestadores e Visitantes:** Vinculação inteligente de prestadores e visitantes ao morador responsável.
- **Proteção de Integridade (Banco de Dados):** Uso de restrições `ON DELETE CASCADE` garantindo que a exclusão de um morador remova automaticamente todos os seus registros vinculados, prevenindo dados órfãos.
- **UI/UX Moderna e Segura:** Interface construída nativamente com JavaFX, fugindo do visual padrão do SO. Utiliza `TextFormatters` para validação de inputs em tempo real e proteção contra `ConcurrentModificationException` na manipulação de listas em memória.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (Orientação a Objetos)
- **Interface Gráfica:** JavaFX (Estilização via CSS inline e layouts responsivos com VBox/HBox)
- **Banco de Dados:** MySQL (Suporte a instâncias Locais, Docker ou Nuvem via Aiven)
- **IDE Recomendada:** Visual Studio Code (VS Code)

---

## ⚙️ Pré-requisitos e Configuração do Ambiente

Para rodar este projeto na sua máquina, você precisará:
1. **Java Development Kit (JDK 17 ou superior)**
2. **JavaFX SDK** (compatível com a sua versão do JDK)
3. **MySQL Connector/J** (Driver JDBC)
4. **db.properties** criar e configurar o arquivo com as infos do seu banco

### Códigos para as tabelas no MySql
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
    cargo VARCHAR(50),
    turno VARCHAR(30)
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