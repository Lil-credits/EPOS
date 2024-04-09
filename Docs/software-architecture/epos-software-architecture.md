# EPOS

## Introduction
EPOS (European Proof of Skill) is a research and development project aimed at revolutionizing the process of issuing and managing micro-credentials at Hogeschool Utrecht. Leveraging the European Blockchain Services Infrastructure (EBSI) as its underlying infrastructure, EPOS addresses the significant administrative burden faced by students, institutions, and other stakeholders when enrolling in courses across different universities and European countries.

Micro-credentials are digital badges that represent specific skills or achievements acquired by an individual. Unlike traditional academic transcripts, micro-credentials provide granular insights into a student's capabilities, allowing for more precise recognition of competencies acquired across various educational contexts. EPOS harnesses the power of micro-credentials to create a seamless ecosystem where students can easily transfer and showcase their achievements across different institutions and countries within the European Union.

In this project we will focus on the issuance side of the micro-credentials ecosystem. Which means developing an issuer portal where teachers of the Hogeschool Utrecht are able to issue the micro-cridentials to other students. 


## Architectural Overview
![Architecture Diagram](https://raw.githubusercontent.com/Lil-credits/EPOS/d6d1e60fc6ff4d649df468e9f442f79e327890ab/Docs/software-architecture/diagrams/Context-diagram.svg?token=APDHMT3LA6FOUXGAXTFMAUDGCP53Y)


## System Components
![Container Diagram](https://raw.githubusercontent.com/Lil-credits/EPOS/91c19d55ee484fa0e2f35196c51a1323fbb5190a/Docs/software-architecture/diagrams/Container-diagram.svg?token=APDHMTYS6OFROOAYYETWUPLGCWPZG)

- **Component 1**: Description of responsibilities, interfaces, and dependencies.
- **Component 2**: Description of responsibilities, interfaces, and dependencies.
- ...

## Key Technologies

- **EBSI**: Is the infrastructure which backs the whole micro-credential ecosystem. By utilizing the EBSI infrastructure, EPOS ensures the integrity, transparency, and interoperability of micro-credentials across the European higher education landscape. Through the decentralized nature of blockchain technology, EPOS provides a secure and tamper-proof platform for issuing, verifying, and exchanging micro-credentials, thereby fostering trust and collaboration among educational institutions, employers, and learners.

- **Vue.js**: Vue.js was chosen for its simplicity, flexibility, and robust ecosystem for building user interfaces. It plays a crucial role in developing intuitive and responsive web interfaces for EPOS, ensuring a seamless user experience.

- **Vert.x**: Vert.x was selected for its event-driven and non-blocking architecture, which enables high-performance and scalable backend services. By utilizing Vert.x, EPOS can handle concurrent requests efficiently, ensuring smooth operation under heavy loads.

- **PostgreSQL**: PostgreSQL serves as the relational database management system (RDBMS) for storing and managing structured data within the EPOS ecosystem. Its reliability, extensibility, and advanced features make it an ideal choice for ensuring data integrity and facilitating complex queries.

- **Walt.id**: is used to utilize their wallets and issuance capabilities of verifiable credentials on EBSI. It serves as an integral component for managing user wallets and facilitating the issuance of verifiable credentials within the EPOS ecosystem.


## Design Patterns and Principles
Discussion of applied design patterns or architectural principles (MVC, microservices, RESTful APIs, etc.).

## Data Model
Description of the data model/schema, including entities, relationships, and data flow.

## Deployment Architecture
Explanation of how the system is deployed, including servers, databases, cloud services, etc.

## Security Considerations
Description of security measures implemented (authentication, authorization, encryption, etc.).

## Performance Considerations
Discussion of performance-related decisions (caching, load balancing, etc.).

## Scalability
Explanation of how the system can scale to accommodate increasing loads or user base.


## Development Environment Setup
Instructions on setting up a development environment, including dependencies and build instructions.
