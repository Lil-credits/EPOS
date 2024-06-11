# Dockerfile for init-db service
FROM python:3.8-slim

# Set working directory
WORKDIR /app

# Copy requirements file and script directory
COPY requirements.txt /app/
COPY initialise_database /app/initialise_database

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Command to run the initialization script
CMD ["python", "initialise_database/initialise_database.py"]