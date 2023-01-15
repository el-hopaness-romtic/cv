CREATE TABLE system_item (
  "id"        BIGSERIAL PRIMARY KEY,
  "item_id"   TEXT UNIQUE,
  "url"       VARCHAR(255),
  "date"      TIMESTAMP WITH TIME ZONE NOT NULL,
  "parent_id" BIGINT REFERENCES system_item(id) ON DELETE CASCADE,
  "type"      SMALLINT,
  "size"      BIGINT
)
