import psycopg2
import requests
import json
from psycopg2 import Error

"""Database INFO"""
USER = "postgres"
PASSWORD = "AXEe263eqPFqwy4z"
HOST = "localhost"
PORT = "5432"
DATABASE = "epos"

ORGANISATION = "Hogeschool Utrecht"
TEAMFACULTY = "Natuur & Techniek"
TEAM = "Open-ICT"
COURSE = "Open Innovation Semester 2 2023-2024 Backend"
DOCENTEN = ["Jeroen", "Robert"]
STUDENTEN = ["Roy", "Kyan", "Jasper", "Sebastiaan"]


# Function to establish a connection to PostgreSQL database
def connect_to_db():
    try:
        connection = psycopg2.connect(
            user=USER,
            password=PASSWORD,
            host=HOST,
            port=PORT,
            database=DATABASE,
        )
        return connection
    except (Exception, Error) as error:
        print("Error while connecting to PostgreSQL", error)
        return None


# Function to populate the database
def populate_database(connection):
    try:
        # Start transaction
        connection.autocommit = False
        cursor = connection.cursor()

        # Create a new organisation
        cursor.execute(
            f"INSERT INTO Organisation (Name) VALUES ('{ORGANISATION}') RETURNING id"
        )
        organisation_id = cursor.fetchone()[0]

        # Create a team faculty
        cursor.execute(
            f"INSERT INTO TeamFaculty (Name, OrgansationId) VALUES ('{TEAMFACULTY}', {organisation_id}) RETURNING id"
        )
        faculty_id = cursor.fetchone()[0]

        # Create a team
        cursor.execute(
            f"INSERT INTO Teams (Name, FacultyId) VALUES ('{TEAM}', {faculty_id}) RETURNING id"
        )
        team_id = cursor.fetchone()[0]

        # Create the users for the team
        for docent in DOCENTEN:

            cursor.execute(
                f"INSERT INTO Users (Name) VALUES ('{docent}') RETURNING id"
            )

            user_id = cursor.fetchone()[0]

            # Add user to the team
            cursor.execute(
                f"INSERT INTO TeamUsers (TeamId, UserId) VALUES ({team_id}, {user_id})"
            )

        # Create users for the students
        for student in STUDENTEN:
            cursor.execute(f"INSERT INTO Users (Name) VALUES ('{student}')")

        # Commit transaction if all queries succeed
        connection.commit()
        print("Database populated successfully")

    except Exception as e:
        # Rollback transaction if any error occurs
        print("Error:", e)
        connection.rollback()

    finally:
        # Reset autocommit mode
        connection.autocommit = True


# Main function
def main():
    connection = connect_to_db()
    if connection:
        populate_database(connection)
        connection.close()


if __name__ == "__main__":
    main()
