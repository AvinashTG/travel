create table user_details(
	id number primary key,
    name varchar2(250) not null,
    address varchar2(500),
    phone_number varchar2(30),
    status varchar(21),
    constraint status_chk check (status in ('A','I'))
);
create sequence user_details_seq INCREMENT by 1 start with 1000;

comment on column user_details.id is 'User ID. Primary key';
comment on column user_details.name is 'User Name';
comment on column user_details.address is 'Full postal address: [doorNo],[street],[city],[pin]';
comment on column user_details.phone_number is '10 digits phone number without space';
comment on column user_details.status is 'A -> Active, I -> In-active';

create table driver_details(
	id number primary key,
	license_number varchar2(50),
    expiry_date date,
    dob date,
    gender varchar2(1),
    user_id number,
    constraint license_number_uk UNIQUE (license_number),
    constraint gender_chk check (gender in ('M','F','O')),
    CONSTRAINT driver_details_fk FOREIGN KEY (user_id) REFERENCES user_details(id)
);
create sequence driver_details_seq INCREMENT by 1 start with 2000;

comment on column driver_details.id is 'User ID. Primary key';
comment on column driver_details.license_number is 'Drivers license number';
comment on column driver_details.expiry_date is 'License expiry date';
comment on column driver_details.dob is 'Driver date of birth';
comment on column driver_details.gender is 'M -> Male, F -> Female, O -> Other';
comment on column driver_details.user_id is 'Foreign key references to USER_DETAILS.ID';

create table vehicle_details(
	id number primary key,
    reg_number varchar2(15),
    name varchar2(250),
    model_year number,
    no_of_seats number,
    ac varchar2(1),
    tax_expiry_date date,
    rc_expiry_date date,
    insurance_expiry_date date,
    status varchar2(1),
    constraint reg_number_uk UNIQUE (reg_number),
    constraint ac_chk check (ac in ('Y','N')),
    constraint veh_status_chk check (status in ('A','I'))
);
create sequence vehicle_details_seq INCREMENT by 1 start with 3000;
comment on column vehicle_details.id is 'Vehicle ID. Primary key';
comment on column vehicle_details.reg_number is 'Vehicle registration number without space and special characters';
comment on column vehicle_details.name is 'Vehicle name';
comment on column vehicle_details.model_year is 'Vehicle model year';
comment on column vehicle_details.no_of_seats is 'Number of seats available in the vehicle';
comment on column vehicle_details.ac is 'Y -> AC, N -> Non-AC';
comment on column vehicle_details.tax_expiry_date is 'Tax expiry date';
comment on column vehicle_details.rc_expiry_date is 'RC expiry date';
comment on column vehicle_details.insurance_expiry_date is 'Insurance expiry date';
comment on column vehicle_details.status is 'A -> Active, I -> In-active';

create table trip_details (
	id number primary key,
    customer_id number,
    from_location varchar2(250),
	to_location varchar2(250),
    start_date_time TIMESTAMP,
    end_date_time TIMESTAMP,
    no_of_passengers number,
    required_ac varchar2(1),
    booking_type varchar2(5),
    meter_start number,
    meter_end number,
    rent number,
    diesel number,
    driver_allowance number,
    toll number,
    parking number,
    other_fee number,
    driver_id number,
    remarks varchar2(500),
    constraint customer_id_fk foreign key (customer_id) references user_details(id),
    constraint required_ac_chk check (required_ac in ('Y','N')),
    constraint booking_type_chk check (booking_type in ('FD','HD','KM','FX')),
    CONSTRAINT driver_id_fk FOREIGN KEY (driver_id) REFERENCES user_details(id)
);
create sequence trip_details_seq INCREMENT by 1 start with 4000;
comment on column trip_details.id is 'Trip ID. Primary key';
comment on column trip_details.customer_id is 'Customer ID references to USER_DETAILS.ID';
comment on column trip_details.from_location is 'Trip start location';
comment on column trip_details.to_location is 'Trip end location';
comment on column trip_details.start_date_time is 'Trip start date and time';
comment on column trip_details.end_date_time is 'Trip end date and time';
comment on column trip_details.no_of_passengers is 'Number of passengers for the trip';
comment on column trip_details.required_ac is 'Y -> AC, N -> Non-AC';
comment on column trip_details.booking_type is 'FD -> Full Day, HD -> Half Day, KM -> Kilo Meter, FX -> Fixed';
comment on column trip_details.meter_start is 'Meter reading at the start of the trip';
comment on column trip_details.meter_end is 'Meter reading at the end of the trip';
comment on column trip_details.rent is 'Rent amount for the trip';
comment on column trip_details.diesel is 'Diesel amount for the trip';
comment on column trip_details.driver_allowance is 'Driver allowance for the trip';
comment on column trip_details.toll is 'Toll amount for the trip';
comment on column trip_details.parking is 'Parking amount for the trip';
comment on column trip_details.other_fee is 'Other fees for the trip';
comment on column trip_details.driver_id is 'Driver ID references to USER_DETAILS.ID';
comment on column trip_details.remarks is 'Additional remarks for the trip';

create table payment_details(
	id number primary key,
    amount number,
    date_time TIMESTAMP,
    payment_mode varchar2(1),
    payment_type varchar2(5),
    trip_id number,
    paid_by varchar2(250),
    received_by varchar2(250),
    payment_for varchar2(5),
    remarks varchar2(500),
    constraint payment_mode_chk check (payment_mode in ('CASH','UPI')),
    constraint payment_type_chk check (payment_type in('AD','FL','PR','CN')),
    CONSTRAINT trip_id_fk FOREIGN KEY (trip_id) REFERENCES trip_details(id),
    constraint payment_for_chk check (payment_for in('DRV','CUS','TAX','RC','SRV','OT'))
);
create sequence payment_details_seq INCREMENT by 1 start with 5000;
comment on column payment_details.id is 'Payment ID. Primary key';
comment on column payment_details.amount is 'Payment amount';
comment on column payment_details.date_time is 'Payment date and time';
comment on column payment_details.payment_mode is 'CASH -> Cash payment, UPI -> UPI payment';
comment on column payment_details.payment_type is 'AD -> Advance, FL -> Full Pay, PR -> Partial Pay, CN -> Cancelled Pay';
comment on column payment_details.trip_id is 'Trip ID references to TRIP_DETAILS.ID';
comment on column payment_details.paid_by is 'Name of the person who paid';
comment on column payment_details.received_by is 'Name of the person who received the payment';
comment on column payment_details.payment_for is 'DRV -> Driver, CUS -> Customer, TAX -> Tax, RC -> Reg Cert, SRV -> Service, OT -> Others';
comment on column payment_details.remarks is 'Additional remarks for the payment';

CREATE TABLE app_user(
  id number,
  username varchar2(250) NOT NULL,
  password varchar2(250) NOT NULL,
  constraint app_user_pk PRIMARY KEY (id),
  constraint app_user_uk UNIQUE (username)
);
create sequence app_user_seq INCREMENT by 1;
comment on column app_user.id is 'User ID. Primary key';
comment on column app_user.username is 'Username for application login';
comment on column app_user.password is 'Password for application login';