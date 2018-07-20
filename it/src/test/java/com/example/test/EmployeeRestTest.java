package com.example.test;

import com.example.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeRestTest
{
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createEmployee() throws Exception
    {
        final ResponseEntity<Employee> response = this.testRestTemplate.getForEntity("http://localhost:8080/employees/1", Employee.class);
        org.junit.Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    private Employee createTestEmployee()
    {
        final Employee employee = new Employee();
        employee.setName("Kerry");
        employee.setDeleted(false);

        return employee;
    }
}
