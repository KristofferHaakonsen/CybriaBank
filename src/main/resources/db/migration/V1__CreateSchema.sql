create schema cybriabank;

CREATE USER ${appUser} WITH PASSWORD '${appPassword}';

grant usage on schema cybriabank to ${appUser};

ALTER USER ${appUser} SET search_path TO cybriabank;