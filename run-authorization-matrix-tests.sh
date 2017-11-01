#!/usr/bin/env bash
echo "Generate a secret for the JWT token signature..."
< /dev/urandom tr -dc _A-Z-a-z-0-9 | head -c50 > jwt-secret.txt
echo ""
echo "Execute the integration tests suite that will validate the authorization matrix..."
mvn clean verify