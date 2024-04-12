# EPOS

## Introduction
EPOS (European Proof of Skill) is a research and development project aimed at revolutionizing the process of issuing and managing micro-credentials at Hogeschool Utrecht. Leveraging the European Blockchain Services Infrastructure (EBSI) as its underlying infrastructure, EPOS addresses the significant administrative burden faced by students, institutions, and other stakeholders when enrolling in courses across different universities and European countries.

Micro-credentials are digital badges that represent specific skills or achievements acquired by an individual. Unlike traditional academic transcripts, micro-credentials provide granular insights into a student's capabilities, allowing for more precise recognition of competencies acquired across various educational contexts. EPOS harnesses the power of micro-credentials to create a seamless ecosystem where students can easily transfer and showcase their achievements across different institutions and countries within the European Union.

In this project we will focus on the issuance side of the micro-credentials ecosystem. Which means developing an issuer portal where teachers of the Hogeschool Utrecht are able to issue the micro-cridentials to other students. 


## Architectural Overview
![Architecture Diagram](https://github.com/Lil-credits/EPOS/raw/docs/Docs/software-architecture/diagrams/Context-diagram.svg)

## System Components

The EPOS (Educational Platform for Open Standards) comprises several key components, each carefully chosen to fulfill specific requirements and objectives. Below is an overview of these components along with the rationale behind their selection:

![Container Diagram](https://github.com/Lil-credits/EPOS/raw/docs/Docs/software-architecture/diagrams/Container-diagram.svg)

- **Education Web Portal**: 
  - This component serves as the user interface (UI) where teachers can seamlessly create modules and issue micro-credentials to their students. It provides an intuitive platform for managing educational content and interactions.
  - Rationale: The Education Web Portal is designed to offer a user-friendly interface for educators to efficiently manage their courses and issue micro-credentials. By centralizing these functions in a dedicated portal, we enhance user experience and streamline administrative tasks.

- **Education Web Portal API**: 
  - Serving as the backend server of the web portal, this component is built using vert.x, a powerful toolkit for building reactive applications on the Java Virtual Machine (JVM). The API facilitates seamless communication between the frontend UI and various backend services, ensuring smooth operation and responsiveness.
  - **Rationale**: We opted for a separate Web Portal API to enhance the maintainability and scalability of the project. By decoupling the frontend and backend, we ensure better code organization and easier updates. Additionally, vert.x's reactive architecture enables efficient handling of concurrent requests, enhancing scalability and responsiveness.

- **Issuer Service**: 
  - The Issuer Service, often referred to as the organizational wallet, is a critical component responsible for issuing verifiable credentials. This component is open-source software developed by walt.id, configured, and deployed in our environment. Our Education Web Portal API depends on this issuer service to issue micro-credentials on EBSI.
  - **Rationale**: While the issuer service provided by walt.id offers robust functionality for issuing credentials, we chose not to build our entire backend on top of it to maintain flexibility and control over our system's architecture. By integrating with the issuer service as a separate component, we can adapt and scale our platform independently, ensuring agility and future-proofing.

- **Wallet**: 
  - This component represents the personal wallet of a user within the EPOS ecosystem. It serves as a secure repository for storing and managing verifiable credentials and other relevant information. By providing users with control over their own data and credentials, the wallet empowers them to engage confidently within the educational landscape.
  - **Rationale**: The inclusion of a personal wallet for users enhances data privacy and security by decentralizing credential storage and management. By adopting a user-centric approach to credential management, we prioritize data sovereignty and empower individuals to maintain control over their digital identities.
- **PostgreSQL**:
    - PostgreSQL serves as the relational database management system (RDBMS) for storing and managing structured data within the EPOS ecosystem. It is only being used by the issuer service. In the future, we want to leverage the EBSI blockchain, so we need to research what to store in the database and what to store on EBSI, taking into account scalability, privacy, and other factors.
    - **Rationale**: Utilizing PostgreSQL for data storage provides reliability, scalability, and flexibility for managing structured data within the issuer service. As we explore integrating with the EBSI blockchain in the future, careful consideration of data storage requirements will be essential to optimize performance, scalability, and data privacy while leveraging the benefits of blockchain technology.

Through thoughtful consideration of these components and their respective roles within the system architecture, we aim to create a robust, scalable, and user-centric educational platform that leverages the latest technologies and standards while maintaining flexibility and control over our infrastructure.

Together, these components form the backbone of the EPOS platform, enabling seamless collaboration, credential issuance, and data management within the educational domain.

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
