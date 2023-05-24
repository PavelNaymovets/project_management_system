#!/bin/bash
set -e

echo "Creating databases..."
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
	CREATE DATABASE "project-db";
EOSQL
echo "Databases created!"
