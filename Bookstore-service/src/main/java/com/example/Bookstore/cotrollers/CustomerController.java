package com.example.Bookstore.cotrollers;

import com.example.Bookstore.models.Book;
import com.example.Bookstore.models.Customer;
import com.example.Bookstore.repository.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("Customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("Customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @PostMapping("Customer/add")
    public ResponseEntity<Integer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("Customer/delete/{id}")
    public ResponseEntity<Integer> deleteCustomer(@PathVariable int id){
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @PutMapping("Customer/update")
    public ResponseEntity<Integer> updateCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("Customer/books/{id}")
    public ResponseEntity<List<Book>> showCustomerBooks(@PathVariable int id){
        return new ResponseEntity<>(customerService.showCustomerBooks(id), HttpStatus.OK);
    }

    @PutMapping("Customer/books/lend")
    public ResponseEntity<Boolean> lendBookToCustomer(@RequestParam int customerId, @RequestParam int bookId){
        return new ResponseEntity<>(customerService.addBookToCustomer(customerId, bookId), HttpStatus.OK);
    }

    @PutMapping("Customer/books/return")
    public ResponseEntity<Boolean> returnBookToLibrary(@RequestParam int customerId, @RequestParam int bookId){
        return new ResponseEntity<>(customerService.getBookFromCustomer(bookId, customerId), HttpStatus.OK);
    }
}