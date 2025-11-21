CREATE TABLE users(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
--    is_admin BOOLEAN DEFAULT FALSE,
    role VARCHAR(20) DEFAULT 'USER',
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();


INSERT INTO users
    SELECT id, is_deleted, case when role is null then 'USER' else role end as role, name, email, password, phone, address, created_at, updated_at
    FROM adopters
;

DROP TABLE adopters;

INSERT INTO users (is_deleted, role, name, email, password)
VALUES (false, 'ADMIN', 'ADMIN', 'admin@anjosprotetores.com.br', '$2a$10$Xj8FzDe06shb6frlMIR9FOKnqTSPItckiKjNnDUK7Y2Q0DwBZBv7O')
