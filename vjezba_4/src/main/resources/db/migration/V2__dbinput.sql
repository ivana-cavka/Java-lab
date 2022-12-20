INSERT INTO adress (id, street, city, postcode)
VALUES (5, 'Ivanina', 'Kaštela', '21215');
INSERT INTO client (id, first_name, last_name, adress_id)
VALUES (20, 'Ivana', 'Ivanic', 5);
INSERT INTO device (id, name, client_id)
VALUES (16, 'brojilo10245', 20);
INSERT INTO electricity_data (id, date, time, value, device_id)
VALUES (55, '21.02.2022.', '15:45', 65, 16);