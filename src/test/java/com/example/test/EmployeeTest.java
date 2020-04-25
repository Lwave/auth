package com.example.test;


import com.example.emp.entity.Employee;
import com.example.emp.service.IEmployeeService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {

    @Autowired
    private IEmployeeService employeeService;

    //查询
    @Test
    public void getById() {
        Employee employee = employeeService.getById(21);
        System.out.println(employee);
        System.out.println(employee);
    }

    //插入
    @Test
    public void save() {
        Employee employee = new Employee();
        employee.setName("nihao");
        employee.setJob("服务员");
        employeeService.save(employee);
    }

    //修改
    @Test
    public void update() {
        Employee byId = employeeService.getById(21);
        byId.setName("fanfan");
        byId.setJob("maiha");
        employeeService.updateById(byId);

    }

    //删除
    @Test
    public void removeId() {
        Employee employee = employeeService.getById(21);
        employeeService.removeById(employee.getId());
    }


}
