-- Team Table
CREATE TABLE IF NOT EXISTS Teams (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL
);

-- EducationModule Table
CREATE TABLE IF NOT EXISTS EducationModule (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  ImageUrl TEXT,
  TeamID INT,
  FOREIGN KEY (TeamID) REFERENCES Teams(Id)
);

-- TeamRelation Table
CREATE TABLE IF NOT EXISTS TeamRelation (
  ParentTeamId INT NOT NULL,
  ChildTeamId INT NOT NULL,
  PRIMARY KEY (ParentTeamId, ChildTeamId),
  FOREIGN KEY (ParentTeamId) REFERENCES Teams(Id),
  FOREIGN KEY (ChildTeamId) REFERENCES Teams(Id)
);




-- User Table
CREATE TABLE IF NOT EXISTS Users (
  Id SERIAL PRIMARY KEY,
  Name VARCHAR(255) NOT NULL
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

-- IssuedMicroCredential Table
CREATE TABLE IF NOT EXISTS IssuedMicroCredential (
  Id SERIAL PRIMARY KEY,
  Issuer INT,
  Receiver INT,
  Status VARCHAR(50),
  EducationModuleVersionID INT,
  FOREIGN KEY (Issuer) REFERENCES Users(Id),
  FOREIGN KEY (Receiver) REFERENCES Users(Id),
  FOREIGN KEY (EducationModuleVersionID) REFERENCES EducationModuleVersion(Id)
);
