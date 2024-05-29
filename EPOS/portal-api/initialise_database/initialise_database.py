import requests

BASE_URL = "http://localhost:8080/api/v1/admin/"
COMPANY = "Hogeschool Utrecht"
SUBSIDIARY = "Natuur & Techniek"
ORGANISATIONAL_UNIT = "Open-ICT"
COURSE = "Open Innovation Semester 2 2023-2024 Backend"
BASE_CREDENTIAL = {
  "courseName": "Nederlands",
  "studyYear": 2021,
  "description": "Dutch language course",
  "imageUrl": "url to image",
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
