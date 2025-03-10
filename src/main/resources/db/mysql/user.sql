CREATE DATABASE IF NOT EXISTS petclinic;

ALTER DATABASE petclinic
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON petclinic.* TO 'petclinic'@'%' IDENTIFIED BY 'petclinic';

# Mysql ver8이상에서 에러나서 추가함
# CREATE USER 'petclinic'@'%' IDENTIFIED BY 'petclinic';
# GRANT ALL PRIVILEGES ON petclinic.* TO 'petclinic'@'%';
# FLUSH PRIVILEGES;
