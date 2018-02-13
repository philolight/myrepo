
CREATE TABLE location
(
  name Varchar(100),
  time_zone_id Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  location_id Varchar(100) NOT NULL
)
;

ALTER TABLE location ADD PRIMARY KEY (location_id)
;

-- Table room

CREATE TABLE room
(
  room_id Varchar(100) NOT NULL,
  name Varchar(100),
  enable Int NOT NULL,
  cdate Varchar(100) NOT NULL,
  udate Varchar(100) NOT NULL,
  location_id Varchar(100)
)
;

CREATE INDEX ix_room__loc_id ON room (location_id)
;

ALTER TABLE room ADD PRIMARY KEY (room_id)
;

-- Table schedule

CREATE TABLE schedule
(
  schedule_id Varchar(100) NOT NULL,
  room_id Varchar(100) NOT NULL,
  name Varchar(100),
  user_id Varchar(100) NOT NULL,
  user_name Varchar(100),
  dept_name Varchar(100),
  sdate Varchar(100) NOT NULL,
  edate Varchar(100) NOT NULL,
  local_year Int NOT NULL,
  local_month Int NOT NULL,
  local_day Int NOT NULL,
  local_day_of_week Int NOT NULL,
  local_shhmm Varchar(100) NOT NULL,
  local_ehhmm Varchar(100) NOT NULL,
  local_duration Int NOT NULL,
  sensor_cnt Int NOT NULL,
  total_sensor Int NOT NULL,
  total_detect Int NOT NULL,
  chk_duration Int NOT NULL,
  result Int NOT NULL,
  cdate Varchar(100) NOT NULL,
  location_id Varchar(100),
  error_cnt Int NOT NULL,
  result_date Varchar(100) NOT NULL
)
;

CREATE INDEX ix_schedule__room_idx ON schedule (room_id)
;

CREATE INDEX ix_schedule_location_id ON schedule (location_id)
;

ALTER TABLE schedule ADD PRIMARY KEY (schedule_id)
;

-- Table slm

CREATE TABLE slm
(
  slm_id Varchar(100) NOT NULL,
  protocol Varchar(100) NOT NULL,
  ip Varchar(100) NOT NULL,
  port Int NOT NULL,
  use_auth Int NOT NULL,
  user_id Varchar(100),
  user_pw Varchar(100),
  cdate Varchar(100) NOT NULL
)
;

ALTER TABLE slm ADD PRIMARY KEY (slm_id)
;

-- Table sensor

CREATE TABLE sensor
(
  slm_id Varchar(100) NOT NULL,
  sensor_id Varchar(100) NOT NULL,
  name Varchar(100),
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_sensor__slm_id ON sensor (slm_id)
;

ALTER TABLE sensor ADD PRIMARY KEY (sensor_id)
;

-- Table room_sensor

CREATE TABLE room_sensor
(
  room_sensor_id Bigint NOT NULL AUTO_INCREMENT,
  room_id Varchar(100) NOT NULL,
  sensor_id Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  udate Varchar(100) NOT NULL,
  status Int NOT NULL,
  location_id Varchar(100),
  PRIMARY KEY (room_sensor_id),
 INDEX ix_room_sensor__status (room_sensor_id,status)
)
;

CREATE INDEX ix_room_sensor__room_id ON room_sensor (room_id)
;

CREATE INDEX ix_room_sensor__sensor_id ON room_sensor (sensor_id)
;

CREATE INDEX ix_room_sensor__location_id ON room_sensor (location_id)
;

-- Table cancel_history

CREATE TABLE cancel_history
(
  reservations Int NOT NULL,
  cancels Int NOT NULL,
  cancel_minutes Int NOT NULL,
  cancel_rate Float NOT NULL,
  cdate Varchar(100) NOT NULL,
  location_id Varchar(100) NOT NULL,
  date_of Varchar(100) NOT NULL,
  reuses Int NOT NULL
)
;

CREATE INDEX ix_daily_report__location_id ON cancel_history (location_id)
;

ALTER TABLE cancel_history ADD PRIMARY KEY (date_of,location_id)
;

-- Table user

CREATE TABLE user
(
  user_id Varchar(100) NOT NULL,
  password Varchar(100) NOT NULL,
  login_count Bigint NOT NULL,
  last_login_time Varchar(100) NOT NULL,
  pw_udate Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

ALTER TABLE user ADD PRIMARY KEY (user_id)
;

-- Table person

CREATE TABLE person
(
  user_id Varchar(100) NOT NULL,
  first_name Varchar(100) NOT NULL,
  last_name Varchar(100) NOT NULL,
  email Varchar(100) NOT NULL,
  phone_number Varchar(100) NOT NULL,
  address Varchar(100),
  language Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_person__user_id ON person (user_id)
;

ALTER TABLE person ADD PRIMARY KEY (user_id)
;

-- Table party

CREATE TABLE party
(
  party_id Varchar(100) NOT NULL,
  description Varchar(1000),
  cdate Varchar(100) NOT NULL
)
;

ALTER TABLE party ADD PRIMARY KEY (party_id)
;

-- Table party_user

CREATE TABLE party_user
(
  user_id Varchar(100) NOT NULL,
  party_id Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_party_user__user_id ON party_user (user_id)
;

CREATE INDEX ix_party_user__party_id ON party_user (party_id)
;

ALTER TABLE party_user ADD PRIMARY KEY (user_id,party_id)
;

-- Table authority

CREATE TABLE authority
(
  authority_id Bigint NOT NULL AUTO_INCREMENT,
  type Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  PRIMARY KEY (authority_id)
)
;

-- Table service

CREATE TABLE service
(
  service_id Varchar(100) NOT NULL,
  upper_service_id Varchar(100) NOT NULL,
  script_name Varchar(100),
  enable Int NOT NULL,
  startable_id Varchar(100),
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_service_startable_id ON service (startable_id)
;

ALTER TABLE service ADD PRIMARY KEY (service_id)
;

-- Table authority_location

CREATE TABLE authority_location
(
  authority_id Bigint NOT NULL,
  location_id Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_user_location__location_id ON authority_location (location_id)
;

CREATE INDEX ix_authority_location__authority_id ON authority_location (authority_id)
;

ALTER TABLE authority_location ADD PRIMARY KEY (location_id,authority_id)
;

-- Table service_authority

CREATE TABLE service_authority
(
  authority_id Bigint NOT NULL,
  service_id Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_service_authority__authority_id ON service_authority (authority_id)
;

CREATE INDEX ix_service_authority__service_id ON service_authority (service_id)
;

ALTER TABLE service_authority ADD PRIMARY KEY (service_id,authority_id)
;

-- Table watchable

CREATE TABLE watchable
(
  watchable_id Varchar(100) NOT NULL,
  upper_watchable_id Varchar(100) NOT NULL,
  time_zone_id Varchar(100) NOT NULL,
  modified_time Varchar(100) NOT NULL,
  type Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

ALTER TABLE watchable ADD PRIMARY KEY (watchable_id)
;

-- Table watchable_history

CREATE TABLE watchable_history
(
  watchable_id Varchar(100) NOT NULL,
  run_count Bigint NOT NULL,
  latency Bigint NOT NULL,
  modified_time Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_watchable_history__watchable_id ON watchable_history (watchable_id)
;

ALTER TABLE watchable_history ADD PRIMARY KEY (watchable_id,cdate)
;

-- Table user_authority

CREATE TABLE user_authority
(
  user_id Varchar(100) NOT NULL,
  authority_id Bigint NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_user_authority__user_id ON user_authority (user_id)
;

CREATE INDEX ix_user_authority__authority_id ON user_authority (authority_id)
;

ALTER TABLE user_authority ADD PRIMARY KEY (user_id,authority_id)
;

-- Table party_authority

CREATE TABLE party_authority
(
  authority_id Bigint NOT NULL,
  cdate Varchar(100) NOT NULL,
  party_id Varchar(100) NOT NULL
)
;

CREATE INDEX ix_party_authority__authority_id ON party_authority (authority_id)
;

CREATE INDEX ix_party_authority__party_id ON party_authority (party_id)
;

ALTER TABLE party_authority ADD PRIMARY KEY (authority_id,party_id)
;

-- Table startable_history

CREATE TABLE startable_history
(
  startable_id Varchar(100) NOT NULL,
  start_succeed Int NOT NULL,
  start_date Varchar(100),
  latency Bigint,
  report Varchar(1000),
  cdate Varchar(100) NOT NULL
)
;

CREATE INDEX ix_startable_history__startable_id ON startable_history (startable_id)
;

ALTER TABLE startable_history ADD PRIMARY KEY (startable_id,cdate)
;

-- Table startable

CREATE TABLE startable
(
  startable_id Varchar(100) NOT NULL,
  type Varchar(100) NOT NULL,
  status Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL
)
;

ALTER TABLE startable ADD PRIMARY KEY (startable_id)
;

-- Table event_history

CREATE TABLE event_history
(
  name Varchar(100) NOT NULL,
  cdate Varchar(100) NOT NULL,
  type Varchar(100),
  times Bigint NOT NULL,
  frequency Float NOT NULL,
  latency Bigint NOT NULL
)
;

ALTER TABLE event_history ADD PRIMARY KEY (name,cdate)
;

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE schedule ADD CONSTRAINT rel_room__schedule FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE ON UPDATE NO ACTION
;


ALTER TABLE room ADD CONSTRAINT rel_loc__room FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE sensor ADD CONSTRAINT rel_slm__sensor FOREIGN KEY (slm_id) REFERENCES slm (slm_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE room_sensor ADD CONSTRAINT rel_room__room_sensor FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE room_sensor ADD CONSTRAINT rel_sensor__room_sensor FOREIGN KEY (sensor_id) REFERENCES sensor (sensor_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE cancel_history ADD CONSTRAINT rel_location__daily_report FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE schedule ADD CONSTRAINT rel_schedule__location FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE room_sensor ADD CONSTRAINT rel_room_sensor__location_id FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE party_user ADD CONSTRAINT rel_user__party_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE service_authority ADD CONSTRAINT rel_authority__service_authority FOREIGN KEY (authority_id) REFERENCES authority (authority_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE service_authority ADD CONSTRAINT rel_service__service_authority FOREIGN KEY (service_id) REFERENCES service (service_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE authority_location ADD CONSTRAINT rel_location__user_location FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE watchable_history ADD CONSTRAINT rel_showable__showable_history FOREIGN KEY (watchable_id) REFERENCES watchable (watchable_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE user_authority ADD CONSTRAINT rel_user__user_authority FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE user_authority ADD CONSTRAINT rel_authority__user_authority FOREIGN KEY (authority_id) REFERENCES authority (authority_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE party_authority ADD CONSTRAINT rel_authority__party_authority FOREIGN KEY (authority_id) REFERENCES authority (authority_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE person ADD CONSTRAINT rel_user__person FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE service ADD CONSTRAINT rel_startable__service FOREIGN KEY (startable_id) REFERENCES startable (startable_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE startable_history ADD CONSTRAINT rel_startable__startable_history FOREIGN KEY (startable_id) REFERENCES startable (startable_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE party_user ADD CONSTRAINT rel_party__party_user FOREIGN KEY (party_id) REFERENCES party (party_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE party_authority ADD CONSTRAINT rel_party__party_authority FOREIGN KEY (party_id) REFERENCES party (party_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER TABLE authority_location ADD CONSTRAINT rel_authority__location FOREIGN KEY (authority_id) REFERENCES authority (authority_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;
