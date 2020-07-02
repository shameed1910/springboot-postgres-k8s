package com.example.k8s.springbootpostgresk8s.controller;

import com.example.k8s.springbootpostgresk8s.Model.Employee;
import com.example.k8s.springbootpostgresk8s.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){

        return employeeRepository.save(employee);
    }
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){

        return  ResponseEntity.ok(employeeRepository.findAll());
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findEmplooyeeId(@PathVariable(value = "id")
                                                    Integer id){
       Employee employee= employeeRepository.findById(id).orElseThrow(
               ()->new ResourceNotFoundException("Employee not found"+id));
               return ResponseEntity.ok().body(employee);

    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                                   @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setName(employeeDetails.getName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);

    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id")
                                                       Integer id){
        Employee employee= employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee not found"+id));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
