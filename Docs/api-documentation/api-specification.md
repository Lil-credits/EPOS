### Education Module API

1. **GET `/api/v1/education-modules/:id`**:
   - **Request Parameters**:
     - `id`: The ID of the education module to retrieve.
   - **Response**:
     - 200 OK: Successfully retrieved the education module.
       - Response Body:
         ```json
         {
           "id": 1,
           "versions": [
             {
               "id": 1,
               "name": "Advanced Computer Science",
               "imageData": "base64-encoded-image-data",
               "baseCredential": {
                 "courseName": "Computer Science",
                 "studyYear": 3,
                 "description": "Advanced course in computer science",
                 "imageUrl": "http://example.com/image.jpg",
                 "requiredAchievements": ["Programming Skills", "Algorithm Analysis"],
                 "skills": ["Programming", "Problem Solving"],
                 "attributes": {
                   "EC": 6,
                   "language": "English",
                   "EQF": 6
                 }
               },
               "effectuationDate": "2024-06-03",
               "createdAt": "2024-06-03T12:00:00",
               "educationModuleId": 1
             }
           ]
         }
         ```
     - 404 Not Found: Education module not found.

2. **GET `/api/v1/education-modules`**:
   - **Response**:
     - 200 OK: Successfully retrieved the list of education modules.
       - Response Body: Array of education modules.
         ```json
         {
           "total": 1,
           "limit": 10,
           "page": 1,
           "educationModules": [
             {
               "id": 1,
               "versions": [
                 {
                   "id": 1,
                   "name": "Advanced Computer Science",
                   "imageData": "base64-encoded-image-data",
                   "baseCredential": {
                     "courseName": "Computer Science",
                     "studyYear": 3,
                     "description": "Advanced course in computer science",
                     "imageUrl": "http://example.com/image.jpg",
                     "requiredAchievements": ["Programming Skills", "Algorithm Analysis"],
                     "skills": ["Programming", "Problem Solving"],
                     "attributes": {
                       "EC": 6,
                       "language": "English",
                       "EQF": 6
                     }
                   },
                   "effectuationDate": "2024-06-03",
                   "createdAt": "2024-06-03T12:00:00",
                   "educationModuleId": 1
                 }
               ]
             }
           ]
         }
         ```

3. **POST `/api/v1/education-modules`**:
   - **Request Body**:
     - JSON object representing the education module.
       - Example Request Body:
         ```json
         {
           "baseCredential": {
             "courseName": "Computer Science",
             "studyYear": 3,
             "description": "Advanced course in computer science",
             "imageUrl": "http://example.com/image.jpg",
             "requiredAchievements": ["Programming Skills", "Algorithm Analysis"],
             "skills": ["Programming", "Problem Solving"],
             "attributes": {
               "EC": 6,
               "language": "English",
               "EQF": 6
             }
           },
           "name": "Advanced Computer Science"
         }
         ```
   - **Response**:
     - 201 Created: Education module created successfully.
       - Response Body:
         ```json
         {
           "id": 1,
           "versions": [
             {
               "id": 1,
               "name": "Advanced Computer Science",
               "imageData": "base64-encoded-image-data",
               "baseCredential": {
                 "courseName": "Computer Science",
                 "studyYear": 3,
                 "description": "Advanced course in computer science",
                 "imageUrl": "http://example.com/image.jpg",
                 "requiredAchievements": ["Programming Skills", "Algorithm Analysis"],
                 "skills": ["Programming", "Problem Solving"],
                 "attributes": {
                   "EC": 6,
                   "language": "English",
                   "EQF": 6
                 }
               },
               "effectuationDate": "2024-06-03",
               "createdAt": "2024-06-03T12:00:00",
               "educationModuleId": 1
             }
           ]
         }
         ```
     - 400 Bad Request: Invalid request body.

### Micro-Credential API

1. **POST `/api/v1/micro-credentials/issue`**:
   - **Request Body**:
     - JSON object representing the micro-credential to be issued.
       - Example Request Body:
         ```json
         {
           "userId": 123,
           "educationModuleVersionId": 456
         }
         ```
   - **Response**:
     - 200 OK: Micro-credential issued successfully.
       - Response Body:
         ```json
         {
           "invitationLink": "http://example.com/invitation"
         }
         ```
     - 400 Bad Request: Invalid request body.

