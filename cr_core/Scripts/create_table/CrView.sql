
CREATE TABLE skin
(
  cdate Varchar(100) NOT NULL,
  skin_id Varchar(100) NOT NULL
)
;

ALTER TABLE skin ADD PRIMARY KEY (skin_id)
;

-- Table field_skin

CREATE TABLE field_skin
(
  field_skin_id Varchar(100) NOT NULL,
  name Varchar(100) NOT NULL,
  type Varchar(100) NOT NULL,
  script_name Varchar(100),
  cdate Varchar(100) NOT NULL,
  visible Int NOT NULL,
  editable Int NOT NULL,
  encryption Int NOT NULL,
  hide_typing Int NOT NULL,
  skin_id Varchar(100) NOT NULL,
  nillable Int NOT NULL,
  auto_fill Int NOT NULL,
  is_pk Int NOT NULL,
  is_fk Int NOT NULL
)
;

CREATE INDEX ix_skin__field_skin_skin_id ON field_skin (skin_id)
;

ALTER TABLE field_skin ADD PRIMARY KEY (field_skin_id)
;

-- Table decimal_range

CREATE TABLE decimal_range
(
  decimal_range_id Bigint NOT NULL AUTO_INCREMENT,
  value_from Bigint NOT NULL,
  value_to Bigint NOT NULL,
  cdate Varchar(100) NOT NULL,
  field_skin_id Varchar(100),
  PRIMARY KEY (decimal_range_id)
)
;

CREATE INDEX ix_decimal_range__field_skin_id ON decimal_range (field_skin_id)
;

-- Table numeric_range

CREATE TABLE numeric_range
(
  numeric_range_id Bigint NOT NULL AUTO_INCREMENT,
  value_from Double NOT NULL,
  value_to Double NOT NULL,
  cdate Varchar(100) NOT NULL,
  field_skin_id Varchar(100),
  PRIMARY KEY (numeric_range_id)
)
;

CREATE INDEX ix_numeric_range__field_skin_id ON numeric_range (field_skin_id)
;

-- Table string_range

CREATE TABLE string_range
(
  length Int NOT NULL,
  regex Varchar(200) NOT NULL,
  cdate Varchar(100) NOT NULL,
  string_range_id Bigint NOT NULL AUTO_INCREMENT,
  script_name Varchar(100),
  field_skin_id Varchar(100),
  PRIMARY KEY (string_range_id)
)
;

CREATE INDEX ix_string_range__field_skin_id ON string_range (field_skin_id)
;

-- Table enum_facet

CREATE TABLE enum_facet
(
  enum_facet_id Bigint NOT NULL AUTO_INCREMENT,
  value Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  script_name Varchar(100),
  field_skin_id Varchar(100),
  PRIMARY KEY (enum_facet_id)
)
;

CREATE INDEX ix_enum_facet__field_skin_id ON enum_facet (field_skin_id)
;

-- Table script

CREATE TABLE script
(
  script_id Bigint NOT NULL AUTO_INCREMENT,
  text Varchar(1000) NOT NULL,
  language Varchar(100),
  script_name Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  PRIMARY KEY (script_id)
)
;

-- Table space

CREATE TABLE space
(
  space_id Bigint NOT NULL AUTO_INCREMENT,
  parent_space_id Bigint,
  name Varchar(100) NOT NULL,
  floor Varchar(100) NOT NULL,
  type Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  PRIMARY KEY (space_id)
)
;

CREATE INDEX is_space__space__space_id ON space (parent_space_id)
;

-- Table driver

CREATE TABLE driver
(
  enable Int NOT NULL,
  driver_id Bigint NOT NULL,
  ping_enable Int NOT NULL,
  alarm_enable Int NOT NULL,
  ping_frequency Bigint NOT NULL,
  name Varchar(100) NOT NULL,
  driver_type_id Varchar(100) NOT NULL,
  poll_enable Int NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_driver_type__driver__driver_type_id ON driver (driver_type_id)
;

ALTER TABLE driver ADD PRIMARY KEY (driver_id)
;

-- Table driver_type

CREATE TABLE driver_type
(
  driver_type_id Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

ALTER TABLE driver_type ADD PRIMARY KEY (driver_type_id)
;

-- Table tcp

CREATE TABLE tcp
(
  ip Varchar(100) NOT NULL,
  port Int NOT NULL,
  tcp_id Bigint NOT NULL AUTO_INCREMENT,
  cdate Varchar(100) NOT NULL,
  driver_id Bigint,
  PRIMARY KEY (tcp_id)
)
;

CREATE INDEX ix_driver__tcp__driver_id ON tcp (driver_id)
;

-- Table point_attribute

CREATE TABLE point_attribute
(
  point_attribute_id Bigint NOT NULL AUTO_INCREMENT,
  name Varchar(100) NOT NULL,
  value Varchar(100) NOT NULL,
  type Char(100) NOT NULL,
  point_id Bigint NOT NULL,
  cdate Varchar(100) NOT NULL,
  PRIMARY KEY (point_attribute_id)
)
;

CREATE INDEX ix_point__point_attribute__point_id ON point_attribute (point_id)
;

-- Table url

CREATE TABLE url
(
  url_id Bigint NOT NULL AUTO_INCREMENT,
  url Varchar(200) NOT NULL,
  auth_enable Int NOT NULL,
  user_name Varchar(100),
  password Varchar(100),
  cdate Varchar(100) NOT NULL,
  driver_id Bigint NOT NULL,
  PRIMARY KEY (url_id,url)
)
;

CREATE INDEX ix_driver__url__driver_id ON url (driver_id)
;

-- Table driver_history

CREATE TABLE driver_history
(
  cdate Varchar(100) NOT NULL,
  driver_id Bigint NOT NULL,
  status Varchar(100),
  fault_cause Varchar(200),
  enable Int,
  ping_enable Int,
  ping_frequency Bigint,
  poll_enable Int,
  poll_frequency Bigint
)
;

CREATE INDEX ix_driver__driver_history__driver_id ON driver_history (driver_id)
;

ALTER TABLE driver_history ADD PRIMARY KEY (cdate,driver_id)
;

-- Table point

CREATE TABLE point
(
  point_id Bigint NOT NULL,
  cdate Varchar(100) NOT NULL,
  name Varchar(100) NOT NULL,
  driver_id Bigint,
  parent_point_id Bigint
)
;

CREATE INDEX ix_driver__point__driver_id ON point (driver_id)
;

CREATE INDEX IX_Relationship8 ON point (parent_point_id)
;

ALTER TABLE point ADD PRIMARY KEY (point_id)
;

-- Table spot

CREATE TABLE spot
(
  x Double NOT NULL,
  y Double NOT NULL,
  z Double NOT NULL,
  spot_id Bigint NOT NULL AUTO_INCREMENT,
  cdate Varchar(100) NOT NULL,
  point_id Bigint NOT NULL,
  name Varchar(100) NOT NULL,
  PRIMARY KEY (spot_id)
)
;

CREATE INDEX ix_point__spot__point_id ON spot (point_id)
;

-- Table alarm

CREATE TABLE alarm
(
  alarm_id Bigint NOT NULL AUTO_INCREMENT,
  cdate Varchar(100) NOT NULL,
  enable Int NOT NULL,
  PRIMARY KEY (alarm_id)
)
;

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE decimal_range ADD CONSTRAINT rel_field_skin__decimal_range FOREIGN KEY (field_skin_id) REFERENCES field_skin (field_skin_id) ON DELETE RESTRICT ON UPDATE NO ACTION
;


ALTER TABLE numeric_range ADD CONSTRAINT rel_field_skin__numeric_range FOREIGN KEY (field_skin_id) REFERENCES field_skin (field_skin_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE string_range ADD CONSTRAINT rel_field_skin__string_range FOREIGN KEY (field_skin_id) REFERENCES field_skin (field_skin_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE enum_facet ADD CONSTRAINT rel_field_skin__enum_facet FOREIGN KEY (field_skin_id) REFERENCES field_skin (field_skin_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE field_skin ADD CONSTRAINT rel_skin__field_skin FOREIGN KEY (skin_id) REFERENCES skin (skin_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE driver_history ADD CONSTRAINT rel_driver__driver_history FOREIGN KEY (driver_id) REFERENCES driver (driver_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE driver ADD CONSTRAINT rel_driver_type__driver FOREIGN KEY (driver_type_id) REFERENCES driver_type (driver_type_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE url ADD CONSTRAINT rel_driver__url FOREIGN KEY (driver_id) REFERENCES driver (driver_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE tcp ADD CONSTRAINT rel_driver__tcp FOREIGN KEY (driver_id) REFERENCES driver (driver_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE point ADD CONSTRAINT rel_driver__point FOREIGN KEY (driver_id) REFERENCES driver (driver_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE point_attribute ADD CONSTRAINT rel_point__point_attribute FOREIGN KEY (point_id) REFERENCES point (point_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE space ADD CONSTRAINT rel_space__space FOREIGN KEY (parent_space_id) REFERENCES space (space_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE spot ADD CONSTRAINT rel_point__spot FOREIGN KEY (point_id) REFERENCES point (point_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE point ADD CONSTRAINT rel_point__point FOREIGN KEY (parent_point_id) REFERENCES point (point_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


