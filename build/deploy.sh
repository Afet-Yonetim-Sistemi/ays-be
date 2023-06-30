#!/bin/bash

echo 'deploy is running'

# connect to the server
echo 'connecting to server'
echo "$PRIVATE_KEY" > private_key && chmod 600 private_key
ssh -o StrictHostKeyChecking=no -i private_key "${USER_NAME}"@"${HOSTNAME}"

# pull the latest changes
echo 'pulling latest changes from github'
cd "${PATH}" || exit
git status
git checkout main &&
git fetch --all &&
git pull origin main

# stop already existing images
echo 'stopping already existing images'
docker ps
docker stop "$(docker ps -aq)"
docker rm "$(docker ps -aq)"
docker rmi "$(docker images -q)"

# run the docker file
echo 'run new docker images'
docker compose -f ../docker-compose-production.yml --env-file ../.env.local up -d