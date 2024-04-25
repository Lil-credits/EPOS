# Use an official Node.js runtime as a parent image
FROM node:lts-alpine

# Install http-server globally
RUN npm install -g http-server

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY ./package*.json ./

# Set working directory to the Vue project
WORKDIR /app/

# Install dependencies
RUN npm install

# Copy all files from the current directory to the working directory
COPY ./ .

# Build the Vue.js application
RUN npm run build

# Expose port 3000 to the outside world
EXPOSE 3000

# Run http-server to serve the built Vue.js application
CMD ["http-server", "-p", "3000", "dist"]