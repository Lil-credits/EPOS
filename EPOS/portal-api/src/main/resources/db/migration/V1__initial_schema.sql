CREATE TABLE IF NOT EXISTS Organisation (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS TeamFaculty (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  OrgansationId INT NOT NULL ,
  FOREIGN KEY (OrgansationId) REFERENCES Organisation(Id)
);

-- Team Table
CREATE TABLE IF NOT EXISTS Teams (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  FacultyId INT NOT NULL,
  FOREIGN KEY (FacultyId) REFERENCES TeamFaculty(Id)
);

-- EducationModule Table
CREATE TABLE IF NOT EXISTS EducationModule (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  ImageUrl TEXT,
  TeamID INT,
  FOREIGN KEY (TeamID) REFERENCES Teams(Id)
);


-- User Table
CREATE TABLE IF NOT EXISTS Users (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  DiD VARCHAR(1024),
  issuanceKey JSONB
);

-- TeamUser Table
CREATE TABLE IF NOT EXISTS TeamUsers (
  TeamId INT NOT NULL,
  UserId INT NOT NULL,
  PRIMARY KEY (TeamId, UserId),
  FOREIGN KEY (TeamId) REFERENCES Teams(Id),
  FOREIGN KEY (UserId) REFERENCES Users(Id)
);

-- EducationModuleVersion Table
CREATE TABLE IF NOT EXISTS EducationModuleVersion (
  Id SERIAL PRIMARY KEY,
  Version INT NOT NULL,
  Description TEXT,
  Attributes JSONB,
  RequiredAchievements JSONB,
  Skills JSONB,
  EducationModuleID INT,
  Status VARCHAR(50),
  FOREIGN KEY (EducationModuleID) REFERENCES EducationModule(Id)
);

CREATE TABLE IF NOT EXISTS IssuedMicroCredential (
  Id SERIAL PRIMARY KEY,
  Issuer INT,
  Receiver INT,
  Status VARCHAR(50),
  EducationModuleVersionID INT,
  Credential JSONB,
  IssuedTime TIMESTAMP,
  FOREIGN KEY (Issuer) REFERENCES Users(Id),
  FOREIGN KEY (Receiver) REFERENCES Users(Id),
  FOREIGN KEY (EducationModuleVersionID) REFERENCES EducationModuleVersion(Id)
);
