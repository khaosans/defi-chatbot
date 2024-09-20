#!/bin/bash

# Step 1: Run repopack with scan and build
echo "Running repopack scan and build..."
repopack --scan --update --build

# Step 2: Check if the build was successful
if [ $? -eq 0 ]; then
  echo "Build successful, starting the server..."

  # Step 3: Kill the running server if it's already running
  SERVER_PID=$(pgrep -f 'your-server-command')
  if [ ! -z "$SERVER_PID" ]; then
    echo "Stopping existing server (PID: $SERVER_PID)..."
    kill -9 $SERVER_PID
  fi

  # Step 4: Start the server
  echo "Starting server..."
  nohup your-server-command > server.log 2>&1 &

else
  echo "Build failed, not starting the server."
fi