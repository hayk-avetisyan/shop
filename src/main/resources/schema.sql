-- Create the Message table
CREATE TABLE message (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    seen BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

-- Create the Product table
CREATE TABLE product (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (id)
);

-- Create the ProductCategory table
CREATE TABLE product_category (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    cover_image VARCHAR(255),
    cover_video VARCHAR(255),
    PRIMARY KEY (id)
);

-- Create a join table for the many-to-many relationship between Product and ProductCategory
CREATE TABLE product_category_mapping (
    product_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES product_category(id) ON DELETE CASCADE
);

-- Create the OrderContact table
CREATE TABLE order_contact (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Create the Order table
CREATE TABLE shop_order (
    id INT NOT NULL AUTO_INCREMENT,
    contact_id INT NOT NULL,
    price INT NOT NULL,
    done BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (contact_id) REFERENCES order_contact(id) ON DELETE CASCADE
);

-- Create the OrderItem table
CREATE TABLE order_item (
    id INT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES shop_order(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

-- Add indexes for better performance
CREATE INDEX idx_message_seen ON message(seen);
CREATE INDEX idx_order_done ON shop_order(done);
CREATE INDEX idx_product_price ON product(price);
