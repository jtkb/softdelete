package com.example;

import com.example.entity.Employee;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ITEmployeeRestTest
{
    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void createEmployeeTest()
    {
        final Employee employee = createTestEmployee();
        final ResponseEntity<Employee> response = this.testRestTemplate.postForEntity("http://localhost:8080/employees", employee, Employee.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        final Employee newEmployee = response.getBody();
        assertNotNull(newEmployee.getId());
        assertNotEquals(employee.getId(), newEmployee.getId());
    }

    @Test
    public void getEmployeeTest() throws Exception
    {
        final ResponseEntity<Employee> response = this.testRestTemplate.getForEntity("http://localhost:8080/employees/1", Employee.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    private Employee createTestEmployee()
    {
        final Employee employee = new Employee();
        employee.setName("Kerry");
        employee.setDeleted(false);

        return employee;
    }
}
