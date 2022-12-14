-- politician Table
CREATE TABLE politician (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL,
  CONSTRAINT pk_politician PRIMARY KEY (id)
);

ALTER TABLE politician ADD CONSTRAINT uc_politician_email UNIQUE (email);

CREATE UNIQUE INDEX politician_email_index ON politician(email);

CREATE UNIQUE INDEX politician_name_index ON politician(name);

-- Speech table
CREATE TABLE speech (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  text VARCHAR(255),
  speech_date DATE,
  subject VARCHAR(255),
  politician_id BIGINT NOT NULL,
  CONSTRAINT pk_speech PRIMARY KEY (id)
);

CREATE INDEX speech_date_index ON speech(speech_date);

CREATE INDEX speech_subject_index ON speech(subject);

ALTER TABLE speech ADD CONSTRAINT FK_SPEECH_ON_POLITICIAN FOREIGN KEY (politician_id) REFERENCES politician (id);

-- Keyword Table
CREATE TABLE keyword (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  speech_id BIGINT,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_keyword PRIMARY KEY (id)
);

CREATE UNIQUE INDEX keyword_name_index ON keyword(name);

ALTER TABLE keyword ADD CONSTRAINT FK_KEYWORD_ON_SPEECH FOREIGN KEY (speech_id) REFERENCES speech (id);