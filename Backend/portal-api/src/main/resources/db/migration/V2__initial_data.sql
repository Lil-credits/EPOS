-- Insert example data into the Users table
INSERT INTO Users (Name) VALUES ('Alice'), ('Bob'), ('Charlie');

-- Insert example data into the Teams table
INSERT INTO Teams (Name) VALUES ('Team Alpha'), ('Team Beta');

-- Insert example data into the EducationModule table
-- Assuming that 'Team Alpha's ID' is 1 (as it was the first team inserted)
INSERT INTO EducationModule (Name, ImageUrl, TeamID) VALUES ('Module 1', 'http://example.com/image.png', 1);

-- Insert example data into the TeamUsers table
-- This will add all three users to both teams
-- The SELECT statement ensures that we insert the correct Ids from the Users and Teams tables
INSERT INTO TeamUsers (TeamId, UserId)
SELECT team.Id, usr.Id
FROM Teams AS team
       CROSS JOIN Users AS usr;

-- Obtain the Id of the inserted EducationModule for further reference in EducationModuleVersion
-- Assuming the EducationModule 'Module 1's ID' is 1 (as it was the first module inserted)
INSERT INTO EducationModuleVersion (Version, Description, Attributes, RequiredAchievements, Skills, EducationModuleID, Status)
VALUES (1, 'Initial version of Module 1', '{}', '["Achievement1", "Achievement2"]', '["Skill1", "Skill2"]', 1, 'Active');

-- Assuming the Issuer and Receiver are Users 'Alice' and 'Bob' respectively,
-- and the EducationModuleVersion 'Module 1 Version 1's ID' is 1,
-- we will use IDs 1 and 2 for Alice and Bob respectively as they were inserted first.
INSERT INTO IssuedMicroCredential (Issuer, Receiver, Status, EducationModuleVersionID)
VALUES (1, 2, 'Issued', 1);
