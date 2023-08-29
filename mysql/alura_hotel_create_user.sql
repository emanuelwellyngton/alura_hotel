use alura_hotel;

CREATE USER 'alura_hotel_app_user'@'%' IDENTIFIED BY 'Ew@101199';

GRANT INSERT, SELECT, DELETE, UPDATE, EXECUTE, LOCK TABLES, CREATE TEMPORARY TABLES ON alura_hotel.*
TO 'alura_hotel_app_user'@'%';