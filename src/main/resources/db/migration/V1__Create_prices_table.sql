CREATE TABLE prices (
                        ID UUID PRIMARY KEY,
                        BRAND_ID INT NOT NULL,
                        START_DATE TIMESTAMP NOT NULL,
                        END_DATE TIMESTAMP NOT NULL,
                        PRICE_LIST INT NOT NULL,
                        PRODUCT_ID INT NOT NULL,
                        PRIORITY INT,
                        PRICE DECIMAL(10, 2) NOT NULL,
                        CURR CHAR(3)
);
