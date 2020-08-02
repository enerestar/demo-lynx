-- BUG: Meanwhile, for those who already have a damaged container from dockerhub or one based on it, this should bring it back to normal: https://jira.mariadb.org/browse/MDEV-22542
INSERT into mysql.global_priv values ('localhost','mariadb.sys','{"access":0,"plugin":"mysql_native_password","authentication_string":"","account_locked":true,"password_last_changed":0}');
FLUSH privileges;

-- Create a new user so application jdbc can connect to it via mariadb driver
CREATE USER 'user1'@localhost IDENTIFIED BY 'password1';
GRANT ALL PRIVILEGES ON simplewiki.* TO 'user1'@localhost;
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'user1'@localhost;

CREATE USER 'user1'@'%' IDENTIFIED BY 'password1';
GRANT ALL PRIVILEGES ON simplewiki.* TO 'user1'@'%';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'user1'@'%';
