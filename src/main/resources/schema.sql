create table if not exists users  (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

create table if not exists authorities (
  username varchar(256),
  authority varchar(256)
);

create table if not exists boodschappenlijst_item (
  id INT NOT NULL AUTO_INCREMENT,
  done TINYINT(1) NULL,
  item TEXT NULL,
  winkel TEXT NULL,
  PRIMARY KEY (id)
);