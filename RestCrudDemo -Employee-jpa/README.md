Here I started with :
1.the configuration of data source and database driver .
2.moved to defining the employee entity and adding different annotation  like @Entity , @Table , @Id , @Column @GeneratedValue.
3.going to repository layer or dal (data access layer ) EmployeeDAO define the mothod i need to for my services.
4.implement methods in EmployeeDAOImpl (@Repository) by injectig "EntityManager" here and using it , the code written will work with database directly through  connection pool and jpa persistence api. 
5.moved to service EmployeeService define methods that will corresspond to these method in repository layer implemnt following methods :
  -findAll :get all employee in database. 
  -findById :get a specific employee if exist in database.
  -save : save or update employee depend on type of http method and also on the state (lifecycle)of object (new or persisted).
  -deleteById :delete a specific employee given his id.
6.implement this method in EmployeeServiceImpl (@Service) by injecting EmployeeDAOImpl and use methods in it .
7.moving to controller or rest layer EmployeeRestController (@RestController) inject EmployeeServiceImpl and work with it to implement the following :
  7.1. get method to path (/api/employees) and don't provide and path variable will redirect you to get all employess .
  7.2. get method to path (/api/employees/id) and provide and path variable empid will redirect you to get the employee with the provided id .
  7.3. post method to path (/api/employees) and provide employee in request body to save this employee (you don't provide id for employee it is maintained  by repository).
  7.4. put method to path (/api/employees) pass employee in request body to update it (you must provide id in employee).
  7.5. delete method to path (/api/employees/id) and pass id in path variable to get this employee deleted.
