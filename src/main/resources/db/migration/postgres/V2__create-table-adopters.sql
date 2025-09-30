CREATE TABLE adopters (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_adopters_updated_at
BEFORE UPDATE ON adopters
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();