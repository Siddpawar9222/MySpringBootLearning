package com.jdbc.C_SpringJDBC.repository;

import com.jdbc.C_SpringJDBC.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {


    public static final class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setEmail(rs.getString("email"));
            employee.setMobileNo(rs.getString("mobile_no"));
            employee.setGender(rs.getString("gender"));
            employee.setAge(rs.getInt("age"));
            employee.setNationality(rs.getString("nationality"));
            return employee;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    //create
    public  int save(Employee employee){
       String sql = "INSERT INTO employee (id ,name, email, mobile_no, gender, age, nationality) VALUES (? ,?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getEmail(), employee.getMobileNo(), employee.getGender(), employee.getAge(), employee.getNationality());
    }

    // Update
    public int update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, email = ?, mobile_no = ?, gender = ?, age = ?, nationality = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getMobileNo(), employee.getGender(), employee.getAge(), employee.getNationality(), employee.getId());
    }

    // Delete
    public int deleteById(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    //retrive all employee
    public List<Employee> findAll(){
        String sql = "SELECT * FROM employee";
        List<Employee> employeeList = jdbcTemplate.query(sql, new EmployeeMapper());
        return employeeList ;
    }

    public Employee findById(int id){
         String sql = "SELECT * FROM employee WHERE id = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeMapper(), id);
        return  employee ;
    }

    // Batch Insert
    public int[] batchInsert(List<Employee> employees) {

        String sql = "INSERT INTO employee (id ,name, email, mobile_no, gender, age, nationality) VALUES (? ,?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Employee employee = employees.get(i);
                ps.setInt(1, employee.getId());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getMobileNo());
                ps.setString(5, employee.getGender());
                ps.setInt(6, employee.getAge());
                ps.setString(7, employee.getNationality());
            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }

        });
    }



    public int updateByNamedParameter(Employee employee) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", employee.getId());
        map.put("name", employee.getName());
        map.put("email", employee.getEmail());
        map.put("mobileNo", employee.getMobileNo());
        map.put("gender", employee.getGender());
        map.put("age", employee.getAge());
        map.put("nationality", employee.getNationality());

        String sql = "UPDATE employee SET name = :name, email = :email, mobile_no = :mobileNo, " +
                "gender = :gender, age = :age, nationality = :nationality WHERE id = :id";

        return namedParameterJdbcTemplate.update(sql, map);
    }

}

/*
RowMapper :
When I retrieve data from database then JDBC get particular data in the type of ResultSet . so To convert result set data into employee type I have yours EmployeeMapper class.

public int[] batchInsert(List<Employee> employees) :

In the batchInsert method, the int[] array returned by the batchUpdate method consists of the number of rows affected for each batch operation. Each element in the array represents the number of rows affected by the corresponding SQL statement in the batch.

For example, if you batch insert 3 Employee objects, and all three insertions are successful, the int[] array will look like [1, 1, 1], indicating that each of the three insertions affected one row.
 */