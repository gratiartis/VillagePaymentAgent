INSERT INTO HT_DEVICE (IMEI, PRODUCT_NUMBER, MODEL_NUMBER, HT_MOBILE_NUMBER, ALLOCATION) VALUES (123, '3456', '345678', '07944169877', 'no');
INSERT INTO HT_DEVICE (IMEI, PRODUCT_NUMBER, MODEL_NUMBER, HT_MOBILE_NUMBER, ALLOCATION) VALUES (333, '4545566', '6867565', '789999999', 'no');
INSERT INTO HT_DEVICE (IMEI, PRODUCT_NUMBER, MODEL_NUMBER, ALLOCATION, HT_MOBILE_NUMBER, ALLOCATION_DATE) VALUES (444, '5465656767', '67676767', 'yes', '077777777', '2010-09-28');

INSERT INTO HT_COUNTRY (TITLE, DESCRIPTION) VALUES ('tanzania', 'Tanzania');
INSERT INTO HT_COUNTRY (TITLE, DESCRIPTION) VALUES ('colombia', 'Colombia');
INSERT INTO HT_COUNTRY (TITLE, DESCRIPTION) VALUES ('uganda', 'Uganda');

INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('blue', 'Blue', 1);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('red', 'Red', 1);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('green', 'Green', 1);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('northern', 'Northern', 2);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('southern', 'Southern', 2);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('eastern', 'Eastern', 2);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('central', 'Central', 3);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('western', 'Western', 3);
INSERT INTO HT_REGION (TITLE, DESCRIPTION, HT_COUNTRY_IDCOUNTRY) VALUES ('south-western', 'South-western', 3);

INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('east blue', 'East Blue', 1);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('west blue', 'West Blue', 1);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('east red', 'East Red', 2);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('west red', 'West Red', 2);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('east green', 'East Green', 3);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('west green', 'West Green', 3);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('east kent', 'East Kent', 4);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('west kent', 'West Kent', 4);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('north devon', 'North Devon', 5);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('south devon', 'South Devon', 5);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('lake district', 'Lake District', 6);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('newham', 'Newham', 6);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('greenwich', 'Greenwich', 7);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('west bank', 'West Bank', 7);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('east bank', 'East Bank', 8);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('central bank', 'Central Bank', 8);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('south bank', 'South Bank', 9);
INSERT INTO HT_DISTRICT (TITLE, DESCRIPTION, HT_REGION_IDREGION) VALUES ('barking', 'Barking', 9);

INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('gender', 'm', 'Male');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('gender', 'f', 'Female');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('education level', 'level 1', 'Level 1');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('education level', 'level 2', 'Level 2');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('education type', 'type 1', 'Type 1');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('education type', 'type 2', 'Type 2');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employment status', 'preregistered', 'Preregistered');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employment status', 'registered', 'Registered');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employment status', 'verified', 'Verified');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employment status', 'employed', 'Employed');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employment status', 'failed', 'Failed');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employee type', 'vpa', 'Village Payment Agent');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employee type', 'ver', 'Verifier');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employee type', 'far', 'Farmer');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('employee type', 'fom', 'Field Operative Manager');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('verification status', 'verified', 'Verified');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('verification status', 'unverified', 'Unverified');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('verification status', 'awaiting verification', 'Awaiting Verification');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('identity document type', 'passport', 'Passport');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('identity document type', 'identity card', 'Identity Card');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('identity document type', 'driving licence', 'Driving Licence');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('title', 'mr', 'Mr');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('title', 'mrs', 'Mrs');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('title', 'miss', 'Miss');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('interview status', 'awaiting arrangement', 'Awaiting Arrangement');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('interview status', 'arranged', 'Arranged');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('interview status', 'completed', 'Completed');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('interview status', 'cancelled', 'Cancelled');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('device allocation', 'yes', 'Yes');
INSERT INTO HT_STATIC_DATA ("TYPE", "VALUE", DESCRIPTION) VALUES ('device allocation', 'no', 'No');

INSERT INTO HT_FOM ("FIRST_NAME", "LAST_NAME", "EMAIL", "PASSWORD") VALUES ('First', 'Last', 'fom@example.org', 'password')
