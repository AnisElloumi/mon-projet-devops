# mon-projet-devops 🚀

> Pipeline CI/CD complet avec Jenkins, Docker et Maven — 
> Atelier DevOps 2025-2026

![Jenkins](https://img.shields.io/badge/Jenkins-2.555.3-D24939?logo=jenkins)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker)
![Node.js](https://img.shields.io/badge/Node.js-20-339933?logo=node.js)
![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=java)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apache-maven)

---

## 📋 Description

Ce projet illustre la mise en place d'un pipeline CI/CD complet avec Jenkins.
À chaque `git push` sur la branche `main`, le pipeline :

1. Lance les tests **Node.js** et **Java/Maven** en parallèle
2. Bloque la livraison si un test échoue
3. Construit une image **Docker** de l'application
4. Publie l'image sur **Docker Hub** avec traçabilité par numéro de build
5. Notifie le résultat (succès ou échec)

---

## 🏗️ Architecture

git push
│
▼
GitHub (webhook)
│
▼
Jenkins Pipeline
├── Tests Node.js (conteneur node:20)     ┐
└── Tests Maven/Java (conteneur maven:3.9) ┘ en parallèle
│
▼
Build image Docker
│
▼
Push → Docker Hub (aniselloumi/mon-projet-devops)

---

## 📁 Structure du projet

mon-projet-devops/
├── Jenkinsfile              # Pipeline CI/CD déclaratif
├── Dockerfile               # Image Docker de l'application
├── index.js                 # Application Node.js
├── package.json             # Dépendances Node.js
├── test.js                  # Tests Node.js
└── java-app/
├── pom.xml              # Configuration Maven
└── src/
├── main/java/com/devops/
│   └── Calculator.java      # Logique métier Java
└── test/java/com/devops/
└── CalculatorTest.java  # Tests JUnit 5

---

## ⚙️ Pipeline Jenkins

Le pipeline est défini en **Declarative Pipeline** dans le `Jenkinsfile`.

### Stages

| Stage | Description | Technologie |
|---|---|---|
| Checkout | Récupération du code depuis GitHub | Git |
| Tests Node.js | npm install + npm test | node:20 (Docker) |
| Tests Maven/Java | mvn clean test + rapport JUnit | maven:3.9 (Docker) |
| Build image Docker | Construction de l'image taguée | Docker |
| Push image | Publication sur Docker Hub | Docker Hub |

### Déclencheur

Le pipeline se déclenche automatiquement via un **webhook GitHub** 
à chaque push sur `main` — sans intervention manuelle.

### Gestion des secrets

Les credentials Docker Hub sont stockés dans le **Jenkins Credentials Store**
(`dockerhub-creds`) et injectés via `withCredentials` — 
jamais en clair dans le code ou les logs.

---

## 🐳 Docker

### Image publiée

Chaque build produit deux tags :
- `:latest` — dernière version stable
- `:<numéro-de-build>` — version traçable (ex: `:19`)

### Lancer l'application localement

```bash
docker run -p 3000:3000 aniselloumi/mon-projet-devops:latest
```

---

## 🧪 Tests

### Node.js

```bash
npm install
npm test
```

### Java / Maven

```bash
cd java-app
mvn clean test
```

Les rapports JUnit sont générés dans 
`java-app/target/surefire-reports/` et publiés 
automatiquement dans Jenkins après chaque build.

---

## 🔒 Sécurité

- Aucun secret en clair dans le code ou le Jenkinsfile
- Credentials stockés dans Jenkins Credentials Store
- `docker logout` systématique après chaque push
- Dépôt public : aucune information sensible versionnée

---

## 🚀 Reproduire ce pipeline

### Prérequis

- Docker installé et démarré
- Un compte GitHub et Docker Hub
- ngrok (pour exposer Jenkins localement)

### Installation Jenkins

```bash
docker volume create jenkins_home

docker run -d --name jenkins \
  -p 8080:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -u root jenkins/jenkins:lts
```

### Plugins Jenkins requis

- Pipeline
- Docker Pipeline
- Git

---

## 📚 Contexte

| | |
|---|---|
| **Formation** | Atelier Jenkins — CI/CD |
| **Encadrant** | Nissen Masmoudi |
| **Auteur** | Anis Elloumi |
| **Année** | 2025-2026 |
| **Durée** | 4h30 |

---

## 📦 Docker Hub

👉 [aniselloumi/mon-projet-devops](https://hub.docker.com/r/aniselloumi/mon-projet-devops)
