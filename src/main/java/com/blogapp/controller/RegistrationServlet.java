package com.blogapp.controller;

import com.blogapp.model.Author;
import com.blogapp.model.AuthorRole; // Make sure you have this enum defined
import com.blogapp.service.AuthorService;
import com.blogapp.repository.impl.AuthorRepositoryImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {

    private final AuthorService authorService;

    public RegistrationServlet() {
        // Initialize the repository and service
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl();
        this.authorService = new AuthorService(authorRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_pass");
        String birthdateStr = request.getParameter("birthdateStr");
        String source = request.getParameter("source"); // Get the source parameter
        String roleStr = request.getParameter("role"); // Get the role parameter

        // Check if passwords match
        if (!password.equals(rePassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Parse the birthdate
        LocalDate birthdate;
        try {
            birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            request.setAttribute("error", "Invalid birthdate format!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Check if email already exists
//        if (authorService.findByEmail(email) != null) {
//            request.setAttribute("error", "Email is already registered!");
//            request.getRequestDispatcher("signup.jsp").forward(request, response);
//            return;
//        }

        // Create new Author object
        Author author = new Author();
        author.setName(name);
        author.setEmail(email);
        author.setPassword(hashPassword(password)); // Hash the password here
        author.setBirthdate(birthdate);

        // Set the author role. If no role is specified, default to Contributor.
        AuthorRole role;
        if (roleStr == null || roleStr.isEmpty()) {
            role = AuthorRole.Contributor;
        } else {
            try {
                role = AuthorRole.valueOf(roleStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Invalid role selected!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }
        }
        author.setRole(role);

        // Save the author using the service
        try {
            authorService.addAuthor(author);
            // Check the source and redirect accordingly
            if ("addAuthor".equals(source)) {
                response.sendRedirect("author?action=list"); // Redirect to the author list
            } else {
                response.sendRedirect("login.jsp"); // Redirect to the login page for signup
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    private String hashPassword(String password) {
        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }
}
