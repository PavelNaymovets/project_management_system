#!/bin/bash
set -e

echo "Creating databases..."


echo "Creating database itpm-service"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
	CREATE DATABASE "project";
EOSQL



echo "Databases created!"
