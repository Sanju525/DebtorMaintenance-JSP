# DebtorMaintenance-JSP

## Steps for creating tables (Refer DatabaseConfig Directory)

1. First create a database.
2. Create the user table by copying the sql query from ```user_sql.txt``` and pasting it in MySQLWorkBench or MySQL shell.
3. Then create the debtorform table by copying the sql query from ```debtorform_sql.txt```..
4. Then create the bankform table by copying the sql query from ```bankform_sql.txt```..
5. Then create the transaction table by copying the sql query from ```transaction_sql.txt```..
6. You have successfully created the required tables for the project.

#### Note: 
After creating the required tables, update the SQL required configuration as per your machine/system in path
=> ```DebtorMaintenance/src/main/java/project/bfour/debtormaintenance/dao/MySqlConn.java```.


## Steps to Configure Tomcat:

### Configure TomcatV9.0.56(>9)
Note: If there are any problems occured regarding url path follow the below steps to fix that issue.

#### In Eclipse
1. Stop the tomcat server if any are running.
2. Open Servers directory in Project Explorer.
3. Open the tomcat server directory that has been configured, u will be having a file named 'server.xml'.
4. Open that file, Goto end of file you will see a tag ```<Context docBase="..." path="..."/>```.
5. In that tag, change the path to ```/``` instead of ```/anyString```, (in path u will be having ```/``` continued with ```project name``` remove project name).
6. Save the file and re-run the project on server.

#### In IntellijIDEA
1. Open Tomcat edit configuration.
2. goto deployement tab beside server tab.
3. remove the text from the ```Application context``` field and save.
4. Re-run the project.
