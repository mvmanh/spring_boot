#!/bin/bash

# Find the process ID (PID) using port 8080
PID=$(lsof -t -i:8080)

# Check if PID is not empty
if [ -n "$PID" ]; then
    echo "Process running on port 8080 with PID: $PID"
    
    # Stop the process using the PID
    kill $PID
    
    echo "Process stopped."
else
    echo "No process found running on port 8080."
fi
