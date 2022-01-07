package com.example.Bookstore.repository;

import com.example.Bookstore.models.Book;
import com.example.Bookstore.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public Customer getCustomerById(int id) {
       return customerRepository.getById(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public int addCustomer(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    public int deleteCustomer(int id) {
        customerRepository.deleteById(id);
        return id;
    }

    public int updateCustomer(Customer customer) {
        Customer repoCustomer = customerRepository.getById(customer.getId());
        repoCustomer.setAge(customer.getAge());
        repoCustomer.setBookList(customer.getBookList());
        repoCustomer.setEMail(customer.getEMail());
        repoCustomer.setName(customer.getName());
        repoCustomer.setSurname(customer.getSurname());
        return customerRepository.save(customer).getId();
    }

    public List<Book> showCustomerBooks(int id) {
        return customerRepository.getById(id).getBookList();
    }

    public boolean addBookToCustomer(int customerId, int bookId){
        return Objects.requireNonNull(
                customerRepository
                        .getById(customerId)
                        .getBookList())
                .add(bookRepository.getById(bookId));
    }

    public boolean getBookFromCustomer(int bookId, int customerId) {
      return Objects.requireNonNull(
              customerRepository
                      .getById(customerId)
                      .getBookList())
              .remove(bookRepository.getById(bookId));
    }
}
