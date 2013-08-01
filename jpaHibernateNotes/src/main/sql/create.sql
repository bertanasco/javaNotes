GRANT ALL privileges on *.* TO 'bert'@'localhost';
update mysql.user set password=PASSWORD('testpassword') where user='bert';
 --mysql command to reload credential chages
 --flush privileges;