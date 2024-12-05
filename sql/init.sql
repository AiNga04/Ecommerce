DELIMITER //

CREATE PROCEDURE insert_milk_tea()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 10 DO
        INSERT INTO milk_tea (name, cost, description, image, id_type)
        VALUES (
            CONCAT('MilkTea', i),
            rand() * 100,
            'Description for MilkTea',
            CONCAT(i, '.jpg'),
            1
        );
        SET i = i + 1;
END WHILE;
END //

DELIMITER ;

-- Call the procedure to insert data
CALL insert_milk_tea();