# Afet Yönetim Sistemi (AYS) Backend

## Tech Stack


Framework :
* Core
    * Spring
        * Spring Boot 3
        * Spring Boot Test (Junit)
        * Spring Boot Dev Tools
        * Spring Security
        * Spring Web
        * Spring Boot JOOQ
        * Spring Boot Actuator
    * Spring Data
        * Spring Data JPA
    * Cache
        * Spring Cache

Databases :
* MYSQL
* H2 Database

Technologies :
* Lombok
* Test Containers
* Swagger

Language :
* Java 17

Build Tool :
* Maven

Software Development Process :
* TDD
* Agile Scrum

Version Control :
* Git

Database Change Management :
* Liquibase


# Getting Started

The project has been generated by Spring Initializer.

## Building the project with tests

```
./mvnw clean install
```

## Running the project

```
./mvnw spring-boot:run
```

## Running locally

Don't forget to enable Lombok pre-processing in your IDE.

## Local Swagger URLs

```
http://localhost:9790/public/api/swagger-ui.html
```

```
http://localhost:9790/public/api/docs
```

## Running as Docker Mysql container
Before running the project, you need to run the following command to start the mysql container:

```
docker compose up -d --build mysql
```

If you want to recreate the mysql container, you can run the following command:
```
docker compose up --force-recreate -d --build mysql
```

If you want to stop the mysql container, you can run the following command:
```
docker compose down -v mysql
```

## Running as Docker container

Before running the project, you need to run the following command to start the project container:

```
docker compose up -d --build mysql
```

```
docker compose up -d --build ays-be
```

If you want to recreate the project container, you can run the following command:
```
docker compose up --force-recreate -d --build 
```

If you want to stop the project container, you can run the following command:
```
docker compose down -v 
```

## Running with Docker Compose

You need to have .env.local file in the same directory with docker-compose.yml which will define
env variables used by the compose command - this file should not be committed:

```.env
SPRING_PROFILES_ACTIVE=development
AYS_DB_USERNAME=ays
AYS_DB_PASSWORD=ayspass
AYS_DB_URL=jdbc:mysql://localhost:3306/ays
```

```
docker compose --env-file .env up -d
```

To terminate the containers:

```
docker compose --env-file .env.local down
```

## Running the app with Kubernetes

```
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

You should be able to see the service, deployment and pods. If you want to access the service using internal IP,
try using NodePort instead of LoadBalancer in service.yaml or simply use port forwarding:

```
kubectl port-forward service/ays-be 9790:9790
```

---

# How to Contribute

The project is managed by [GitHub projects](https://github.com/orgs/afet-yonetim-sistemi/projects/3). You can assign
an issue from the Todo list and start working on it.

## Development Standards

This project uses [GitHub flow](https://docs.github.com/en/get-started/quickstart/github-flow) for collaboration.
You can see open issues [here](https://github.com/afet-yonetim-sistemi/ays-be/issues).
You can start working one of the open issues by assigning it to yourself.

For your feature branches, please ensure below:

- Make sure to associate your feature branch with the GitHub issue from 'Development' section in each issue page.
- main branch is protected.
- PR should be linked to the relevant issue within the GitHub Project.
- Your feature branches can be merged by pull request that is approved by at least one code owner.
- PR should be squash-merged to avoid having merge commit history.
- PR title and description should clearly define and explain the aim.
- PR title should contain the issue/feature/bug number e.g. Issue 7 - Readme Update
- PR should successfully complete build and any other necessary tests before being merged to main.
- Branches should follow the below pattern:
    feature/issue-no-other-desc
    bugfix/issue-no-other-desc
    hotfix/issue-no-other-desc (there may also be no issue number for some hotfixes)
- Each release should be tagged with a version
- Commit messages should be subjectless
  e.g. Add README.md
  e.g. Fix ...
- Comments within the PR should only be resolved by the person who made the comment.
- PR should be concise; don't try to fix/introduce more than one thing
- Do not include/push secret/credential information
- The code should comply with the existing code standards and previously established coding standards. No new standards
  should be introduced, and if they are, the reasons for their addition should be stated, with the expectation that this
  structure will be applied to the entire project.

## Docker Image Name Conventions

Docker images should be tagged as "org-name/project-name:version" e.g. "ays/ays-be:v1.0"
"ays/ays-be" indicates the latest version.

### Versioning

The project follows [the semantic versioning](https://semver.org/) i.e. MAJOR.MINOR.PATCH e.g. v1.9.9.

## Project Documents

AYS technical analysis can be
found [here](https://docs.google.com/document/d/1_GyROvXrsD88udD6z_KfF-Q5Cs77YLHDglqafRXIV_o/edit).

For other information, you can contact the project's [Discord channel](https://discord.gg/HeunQcqg).

---

## Postman Collections

### AYS Auth APIs

https://documenter.getpostman.com/view/26813504/2s93kz55K3

### AYS APIs

https://documenter.getpostman.com/view/26813504/2s93kz55Jz

---

---

# Project Infrastucture

<img src="https://bit.ly/3MzggX8" width="7000" alt=""/>
<img src="https://bit.ly/42NeBTQ" width="400" alt=""/>