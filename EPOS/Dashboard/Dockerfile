# Use an official Node.js runtime as a parent image
FROM node:lts-alpine

# Install serve globally
RUN npm install -g serve

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY ./package*.json ./

# Install dependencies
RUN npm install

# Copy all files from the current directory to the working directory
COPY . .

# Build the Vue.js application
RUN npm run build

# Expose port 3000 to the outside world
EXPOSE 3000

# Run serve to serve the built Vue.js application with a fallback for history mode
CMD ["serve", "-s", "dist", "-l", "3000"]
