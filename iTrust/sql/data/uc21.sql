/*Insert patient Sandy Sky*/
INSERT INTO patients
(MID, 
firstName,
lastName, 
email,
address1,
address2,
city,
state,
zip,
phone,
eName,
ePhone,
iCName,
iCAddress1,
iCAddress2,
iCCity, 
ICState,
iCZip,
iCPhone,
iCID,
DateOfBirth,
DateOfDeath,
CauseOfDeath,
MotherMID,
FatherMID,
BloodType,
Ethnicity,
Gender,
TopicalNotes
)
VALUES (
201,
'Sandy',
'Sky',
'sandy.sky@gmail.com',
'123 Sky Street',
'',
'Raleigh',
'NC',
'27607',
'123-456-7890',
'Susan Sky-Walker',
'444-332-4309',
'IC',
'Street1',
'Street2',
'City',
'PA',
'12345-6789',
'555-555-5555',
'1',
DATE(NOW()-INTERVAL 24 YEAR),
NULL,
'',
0,
0,
'O-',
'Caucasian',
'Male',
'Will save the universe, please protect'
)  ON DUPLICATE KEY UPDATE MID = MID;

INSERT INTO users(MID, password, role, sQuestion, sAnswer) 
			VALUES (201, '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4', 'patient', '2+2?', '4')
 ON DUPLICATE KEY UPDATE MID = MID;
 /*password: pw*/
 
 /* Office visits for Sandy Sky */
 INSERT INTO officevisit (
 	visitID,
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID, 
	weight, 
	height,
	blood_pressure,
	household_smoking_status,
	patient_smoking_status,
	hdl,
	ldl,
	triglyceride) 
VALUES (2100, 201, DATE(NOW()-INTERVAL 1 YEAR), 1, 1, 180, 70, '100/70', 1, 4, 40, 81, 105);

INSERT INTO officevisit (
 	visitID,
	patientMID, 
	visitDate, 
	locationID, 
	apptTypeID, 
	weight, 
	height,
	blood_pressure,
	household_smoking_status,
	patient_smoking_status,
	hdl,
	ldl,
	triglyceride) 
VALUES (2101, 201, DATE(NOW()-INTERVAL 6 MONTH), 1, 1, 178, 70, '105/68', 1, 4, 45, 81, 105);

/* Prescription codes for SandySky */
INSERT INTO ndcodes (
	Code,
	Description)
VALUES (05730150, "Advil");
INSERT INTO ndcodes (
	Code,
	Description)
VALUES (483013420, "Midichlomaxene");
INSERT INTO ndcodes (
	Code,
	Description)
VALUES (63739291, "Oyster Shell Calcium with Vitamin D");

/* Actual prescriptions for Sandy Sky */
