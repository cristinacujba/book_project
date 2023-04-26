package com.example.book_project.controller;

import com.example.book_project.model.Book;
import com.example.book_project.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
    public class BookController {

        @Autowired
        private BookServiceImpl bookService;


        // display list of books
        @GetMapping("/")
        public String viewHomePage(Model model) {
            return findPaginated(1,model);
        }

        @GetMapping("/showNewBookForm")
        public String showNewBookForm(Model model) {
            // create model attribute to bind form data
            Book book = new Book();
            model.addAttribute("book", book);
            return "new_book";
        }

        @PostMapping("/saveBook")
        public String saveBook(@ModelAttribute("book") Book book) {
            // save book to database
            bookService.saveBook(book);
            return "redirect:/";
        }
        @GetMapping("/showFormForUpdate/{id}")
        public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
            // get book from the service
            Book book = bookService.getBookById(id);
            // set book as a model attribute to pre-populate the form
            model.addAttribute("book", book);
            return "update_book";
        }
        @GetMapping("/deleteBook/{id}")
        public String deleteBook(@PathVariable (value = "id") int id){
            // call delete book method
            this.bookService.deleteBookById(id);
            return "redirect:/";
        }

        @GetMapping("/page/{pageNo}")
        public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
            int pageSize = 5;
            Page<Book> page = bookService.findPaginated(pageNo,pageSize);
            List<Book> bookList = page.getContent();
            model.addAttribute("currentPage" ,pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("listBooks", bookList);
            return "index";

        }
    }

