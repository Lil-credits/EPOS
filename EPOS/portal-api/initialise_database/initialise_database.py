import requests

BASE_URL = "http://api:8080/api/v1/admin/"
COMPANY = "Hogeschool Utrecht"
SUBSIDIARY = "Natuur & Techniek"
ORGANISATIONAL_UNIT = "Open-ICT"
COURSE = "Open Innovation Semester 2 2023-2024 Backend"
BASE_CREDENTIAL = {
    "courseName": "Nederlands",
    "studyYear": 2021,
    "description": "Dutch language course",
    "imageUrl": "data:image/png;base64,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",
    "requiredAchievements": ["A2", "B1"],
    "skills": ["listening", "speaking", "reading", "1"],
    "attributes": {"EC": 1, "language": "Dutch", "EQF": 1},
}


DOCENTEN = ["Jeroen", "Robert"]
STUDENTEN = ["Roy", "Kyan", "Jasper", "Sebastiaan"]
CLASS = "Open Innovation Semester 2 2023-2024 Backend"


# Function to populate the database
def populate_database():
    # create company
    response = requests.post(
        url=BASE_URL + "company",
        json={"name": COMPANY},
        timeout=5,
    )
    company_id = response.json()["id"]

    # create subsidiary
    response = requests.post(
        url=BASE_URL + "subsidiary",
        json={"name": SUBSIDIARY, "companyId": company_id},
        timeout=5,
    )
    subsidiary_id = response.json()["id"]

    # create organisational unit
    response = requests.post(
        url=BASE_URL + "organisational-unit",
        json={
            "name": ORGANISATIONAL_UNIT,
            "subsidiaryId": subsidiary_id,
            "companyId": company_id,
        },
        timeout=5,
    )
    team_id = response.json()["id"]

    # create education module
    response = requests.post(
        url=BASE_URL + "education-module",
        json={
            "organisationalUnitId": team_id,
            "name": COURSE,
            "imageData": BASE_CREDENTIAL["imageUrl"],
            "baseCredential": BASE_CREDENTIAL,
        },
        timeout=5,
    )
    education_module_version_id = response.json()["id"]
    # create class
    response = requests.post(
        url=BASE_URL + "class",
        json={
            "name": CLASS,
            "educationModuleVersionId": education_module_version_id,
            "organisationalUnitId": team_id,
        },
        timeout=5,
    )
    class_id = response.json()["id"]

    # create docenten and onboard them as members of the organisational unit
    for docent in DOCENTEN:
        response = requests.post(
            url=BASE_URL + "account",
            json={"name": docent, "role": "docent"},
            timeout=5,
        )
        user_id = response.json()["id"]
        response = requests.post(
            url=BASE_URL + "membership",
            json={"accountId": user_id, "organisationalUnitId": team_id},
            timeout=5,
        )

    # create studenten
    for student in STUDENTEN:
        response = requests.post(
            url=BASE_URL + "account",
            json={"name": student, "role": "student"},
            timeout=5,
        )
        user_id = response.json()["id"]


# Main function
def main():
    populate_database()
    print("Database populated")


if __name__ == "__main__":
    main()
