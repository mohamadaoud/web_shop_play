

insert into Category (name) values ('Houses');
insert into Category (name) values ('Electronics');
insert into Category (name) values ('Men and Women Clothes');
insert into Category (name) values ('Services');

insert into Product (name, description, cost, RRP, quantity) values ('Three bedroom flat', 'An awesome 120 km house with big garden and has a nice view', 300000.00, 350000.00, 10);
insert into Product (name, description, cost, RRP, quantity) values ('HP', '2014 super model', 3500.00, 3700.00, 50);
insert into Product (name, description, cost, RRP, quantity) values ('Toshiba', '2013 best model', 3700.00, 4000.00, 90);
insert into Product (name, description, cost, RRP, quantity) values ('Levis Jacket','Size 48 suitable for men', 800.00, 1000.00, 20);
insert into Product (name, description, cost, RRP, quantity) values ('Cleaning', 'Service available 24/7', 300.00, 320.00, 100);


insert into Product_Category (product_id, category_id) values (1, 1);
insert into Product_Category (product_id, category_id) values (2, 2);
insert into Product_Category (product_id, category_id) values (3, 2);
insert into Product_Category (product_id, category_id) values (2, 4);
insert into Product_Category (product_id, category_id) values (3, 4);
insert into Product_Category (product_id, category_id) values (5, 4);
insert into Product_Category (product_id, category_id) values (4, 3);
insert into Product_Category (product_id, category_id) values (5, 3);

insert into User (name, username, password, address, phoneNumber) values ('Mohamad', 'mdaoud', 'admin', 'Stockholm', '011117343');
