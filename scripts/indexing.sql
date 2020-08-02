-- To make sure to add indexing to db after downloading data
-- Create an index in table page, column page_title, so that every join has an index attached to it
-- This is so that it can be BTREE query with time complexity of lg N. 
CREATE index pagetitle_index on page (page_title);

DROP TABLE IF EXISTS `top10`;
-- Create a table to store top 10 category
CREATE TABLE top10 (
    cat_id INT(10) NOT NULL,
    cat_title VARBINARY(255) NOT NULL,
    cat_pages INT(11) NOT NULL,
    cat_subcats INT(11) NOT NULL,
    cat_files INT(11) NOT NULL
);

-- Get top 10 category and insert into top10 table
INSERT INTO top10
SELECT * FROM category
ORDER BY cat_pages DESC limit 10;

-- For faster query, create index
CREATE index cat_title_index on top10 (cat_title);

DROP TABLE IF EXISTS `outdated`;
-- Create a new table to insert the queries of top 10 category most outdated page
CREATE TABLE outdated (
    cat_title VARBINARY(255) NOT NULL,
    from_page_id INT(8) NOT NULL,
    from_page_title VARBINARY(255) NOT NULL,
    to_page_id INT(8) NOT NULL,
    to_page_title VARBINARY(255) NOT NULL,
    diff BIGINT(8) NOT NULL
);