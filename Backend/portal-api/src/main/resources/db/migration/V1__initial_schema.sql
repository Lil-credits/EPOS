
-- EducationModule Table
CREATE TABLE EducationModule (
                               Id SERIAL PRIMARY KEY,
                               Name VARCHAR(255) NOT NULL,
                               ImageUrl TEXT,
                               TeamID INT
);

-- TeamRelation Table
CREATE TABLE TeamRelation (
                            ParentTeamId INT NOT NULL,
                            ChildTeamId INT NOT NULL,
                            PRIMARY KEY (ParentTeamId, ChildTeamId),
                            FOREIGN KEY (ParentTeamId) REFERENCES Teams(Id),
                            FOREIGN KEY (ChildTeamId) REFERENCES Teams(Id)
);

-- Team Table
CREATE TABLE Teams (
                    Id SERIAL PRIMARY KEY,
                    Name VARCHAR(255) NOT NULL
);

-- Update EducationModule table to add the foreign key for TeamID
ALTER TABLE EducationModule
  ADD CONSTRAINT fk_team
    FOREIGN KEY (TeamID) REFERENCES Teams(Id);

-- User Table
CREATE TABLE Users (
                      Id SERIAL PRIMARY KEY,
                      Name VARCHAR(255) NOT NULL
);

-- TeamUser Table
CREATE TABLE TeamUsers (
                        TeamId INT NOT NULL,
                        UserId INT NOT NULL,
                        PRIMARY KEY (TeamId, UserId),
                        FOREIGN KEY (TeamId) REFERENCES Teams(Id),
                        FOREIGN KEY (UserId) REFERENCES Users(Id)
);

-- EducationModuleVersion Table
CREATE TABLE EducationModuleVersion (
                                      Id SERIAL PRIMARY KEY,
                                      Version INT NOT NULL,
                                      Description TEXT,
                                      Attributes JSONB,
                                      RequiredAchievements TEXT[],
                                      Skills TEXT[],
                                      EducationModuleID INT,
                                      Status VARCHAR(50),
                                      FOREIGN KEY (EducationModuleID) REFERENCES EducationModule(Id)
);

-- IssuedMicroCredential Table
CREATE TABLE IssuedMicroCredential (
                                     Id SERIAL PRIMARY KEY,
                                     Issuer INT,
                                     Receiver INT,
                                     Status VARCHAR(50),
                                     EducationModuleVersionID INT,
                                     FOREIGN KEY (Issuer) REFERENCES Users(Id),
                                     FOREIGN KEY (Receiver) REFERENCES Users(Id),
                                     FOREIGN KEY (EducationModuleVersionID) REFERENCES EducationModuleVersion(Id)
);
