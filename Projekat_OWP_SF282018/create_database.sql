drop table if exists Movies;

create table Movies(
    ID integer primary key,
    Title varchar(100) not null,
    Director varchar(100) not null,
    Genre varchar(30) not null,
    Duration int check(Duration > 0) not null,
    Distributor varchar(100) not null,
    Country varchar(50) not null,
    ReleaseDate int check (ReleaseDate > 0) not null,
    Active integer not null
);

insert into Movies (Title, Director, Genre, Duration, Distributor, Country, ReleaseDate, Active) values ('Vertigo','Alfred Hitchcock','Psychological thriller','128','Paramount Pictures','US',1958,1)
insert into Movies (Title, Director, Genre, Duration, Distributor, Country, ReleaseDate, Active) values ('Suicide Squad','David Ayer','Superhero film','123','Warner Bros Pictures','US',2016,1)
insert into Movies (Title, Director, Genre, Duration, Distributor, Country, ReleaseDate, Active) values ('Titanic','James Cameron','Romance','195','Paramount Pictures','US',1958,1)

drop table if exists Users;

create table Users(
    Username varchar(20) primary key,
    Password varchar(20) not null,
    RegistrationDate integer not null,
    Role varchar(15) not null,
    Active integer not null,
    LoggedIn integer not null
);

insert into Users values('admin', 'admin', date('now'), 'ADMIN', 1, 0);
insert into Users values('stefan', 'stefans', date('now'), 'USER', 1, 0);
insert into Users values('petar', 'petarp', date('now'), 'USER', 1, 0);

drop table if exists ProjectionTypes;

create table ProjectionTypes (
    ID integer primary key,
    Type varchar(5) not null
);

drop table if exists Halls;

create table Halls (
    ID integer primary key,
    Name varchar(50) not null,
    NumOfProjections int check(NumOfProjections > 0 and NumOfProjections < 4) not null
);

drop table if exists Seats;

create table Seats (
    SeatNumber integer PRIMARY KEY,
    Hall int not null,
    foreign key(Hall) references Halls(ID),
);

drop table if Exists Projections;

create table Projections (
    ID integer primary key,
    Movie int not null,
    ProjectionType int not null,
    Hall int not null,
    Date date not null,
    Price real check ( Price > 0 ) not null,
    Admin varchar(30) not null,
    Active integer not null,
    foreign key(Movie) references Movies(ID),
    foreign key(ProjectionType) references ProjectionTypes(ID),
    foreign key(Hall) references Halls(ID),
    foreign key(Admin) references Users(Username)
);

drop table if exists Tickets;

create table Tickets (
    ID integer primary key,
    Projection int not null,
    SeatNumber int not null,
    Hall int not null,
    DateSold integer not null,
    User varchar(30) not null,
    Active integer not null,
    foreign key(Projection) references Projections(ID),
    foreign key(SeatNumber) references Seats(Number),
    foreign key(Hall) references Seats(Hall),
    foreign key(User) references Users(Username)
);