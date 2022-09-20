INSERT INTO politician(NAME, EMAIL) VALUES
    ('Some Guy','someguy@gmail.com'), -- 1
    ('Amanda Poker','email@yahoo.com'), -- 2
    ('Ben Derhover','twt@email.com'), -- 3
    ('Rick Grimes','unknowm@email.com'); -- 4

INSERT INTO speech(TEXT,SPEECH_DATE,SUBJECT,POLITICIAN_ID) VALUES
    ('Hello! - This is my first Speech', '02/26/2019', 'first Subject', 1), -- 1
    ('This is the second Speech', '03/12/2019', 'Second Subject', 1),  -- 2
    ('This is my Speech. Go for it!', '03/18/2019', 'My Subject', 2),  -- 3
    ('Some Text blablabla', '03/22/2019', 'Blabla Subject', 3),  -- 4
    ('Third Speech', '03/22/2019', 'Third Subject', 2),  -- 5
    ('Last Speech', '03/30/2019', 'Last Subject', 1);  -- 6

INSERT INTO keyword(speech_id, name) VALUES
    (1,'first'),
    (1,'Hello'),
    (2,'Second'),
    (3,'it'),
    (3,'Go'),
    (4,'bla'),
    (5,'Third'),
    (6,'Last');
