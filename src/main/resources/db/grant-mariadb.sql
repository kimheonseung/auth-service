DROP DATABASE devh;
CREATE DATABASE devh;
CREATE USER IF NOT EXISTS devh@'%' IDENTIFIED BY '1234';
/* localhost -> hostname */
CREATE USER IF NOT EXISTS devh@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON devh.* TO devh@'%' IDENTIFIED BY '1234';
/* localhost -> hostname */
GRANT ALL PRIVILEGES ON devh.* TO devh@'localhost' IDENTIFIED BY '1234';
FLUSH PRIVILEGES;