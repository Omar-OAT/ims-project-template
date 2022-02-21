-- drop schema ims;
-- create schema if not exists ims;
-- use ims;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

create table if not exists `ims`.`items`(
`id` int(11) not null auto_increment,
`item_name` varchar(100),
`item_price` float(11),
primary key(`id`)

);

 create table if not exists `ims`.`orders`(
`OrderID` int(11) not null auto_increment,
`customerID` int(11) not null,
`Ordertotal` int(11) ,
primary key (`OrderID`),
foreign key (`customerID`) references customers(`id`)
);

create table if not exists `ims`.`order_basket`(
`fkItemID` int(11) not null,
`fkorderID` int(11) not null,
`quantity` int(11) default null,
foreign key (`fkItemID`) references items(`id`),
foreign key (`fkorderID`) references orders(`OrderID`)

);

-- SELECT ob.quantity,ob.fkorderID,ob.fkItemID,i.item_name,i.item_price FROM order_basket ob  JOIN items i ON i.id=ob.fkItemID WHERE ob.fkorderID=3;
-- select * from customers;
select * from items;
-- select * from orders;
-- SELECT o.OrderID,o.customerID, ob.fkItemID, ob.quantity FROM orders o, order_basket ob;
-- select  ob.fkItemID, o.customerID, ob.quantity, ob.fkorderID from order_basket ob inner join orders o on o.OrderID = ob.fkorderID ;
-- INSERT INTO orders(customerID) VALUES (?);
-- INSERT INTO order_basket(fkItemID) VALUES (?);
-- SELECT ob.fkorderID,SUM(i.item_price*ob.quantity) AS Ordertotal FROM order_basket ob JOIN items i ON i.id=ob.fkItemID WHERE ob.fkorderID=3 GROUP by ob.fkorderID;
-- DELETE  FROM order_basket WHERE fkItemID = ? AND fkorderID = ?