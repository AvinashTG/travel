CREATE DATABASE travel;

use travel;

create table user_details(
	id int auto_increment primary key comment "Unique identifier for the user details",
    name varchar(250) not null,
    address varchar(500),
    phone_number varchar(30),
    status varchar(1) comment 'A=Active, I=Inactive',
    constraint status_chk check (status in ('A','I'))
);


create table driver_details(
	id int auto_increment primary key comment "Unique identifier for the user details",
	license_number varchar(50) unique key,
    expiry_date date,
    dob date,
    gender varchar(1) comment "M = Male, F = Female, O = Others",
    user_id int,
    constraint gender_chk check (gender in ('M','F','O')),
    CONSTRAINT driver_details_fk FOREIGN KEY (user_id) REFERENCES user_details(id)
);

create table vehicle_details(
	id int auto_increment primary key,
    reg_number varchar(15) unique KEY comment "Registration number without space and speical characters",
    name varchar(250),
    model_year int,
    no_of_seats int,
    ac varchar(1),
    tax_expiry_date date,
    rc_expiry_date date,
    insurance_expiry_date date,
    status varchar(1) comment 'A=Active, I=Inactive',
    constraint ac_chk check (ac in ('Y','N')),
    constraint status_chk check (status in ('A','I'))
);

create table trip_details (
	id int auto_increment primary key,
    customer_id int comment "Id from user_details table",
    from_location varchar(250),
	to_location varchar(250),
    start_date_time datetime,
    end_date_time datetime,
    no_of_passengers int,
    required_ac varchar(1),
    booking_type varchar(5) comment "FD=FullDay,HD=HalfDay,KM=KiloMeter,FX=Fixed",
    meter_start int,
    meter_end int,
    rent decimal,
    diesel decimal,
    driver_allowance decimal,
    toll decimal,
    parking decimal,
    other_fee decimal,
    driver_id int comment "Id from user_details table",
    remarks varchar(500),
    constraint customer_id_fk foreign key (customer_id) references user_details(id),
    constraint required_ac_chk check (required_ac in ('Y','N')),
    constraint booking_type_chk check (booking_type in ('FD','HD','KM','FX')),
    CONSTRAINT driver_id_fk FOREIGN KEY (driver_id) REFERENCES user_details(id)
);

create table payment_details(
	id int auto_increment primary key,
    amount decimal,
    date_time datetime,
    payment_mode varchar(1),
    payment_type varchar(5) comment "AD=Advance,FL=FullPay,PR=PartialPay,CN=CancelledPay",
    trip_id int comment "Id from trip_details table",
    paid_by varchar(250),
    received_by varchar(250),
    payment_for varchar(5) comment "DRV=Driver,CUS=Customer,TAX=Tax,RC=RegCert,SRV=Service,OT=Others",
    remarks varchar(500),
    constraint payment_mode_chk check (payment_mode in ('CASH','UPI')),
    constraint payment_type_chk check (payment_type in('AD','FL','PR','CN')),
    CONSTRAINT trip_id_fk FOREIGN KEY (trip_id) REFERENCES trip_details(id),
    constraint payment_for_chk check (payment_for in('DRV','CUS','TAX','RC','SRV','OT'))
);

CREATE TABLE `app_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ;
