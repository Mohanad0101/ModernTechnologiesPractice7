package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    // HOME PAGE - maps root URL "/"
    @GetMapping("/")
    public String home(Model model) {
        // Welcome message
        model.addAttribute("welcomeMessage", "Welcome to Spring Framework!");
        model.addAttribute("springMessage", "🌸 Spring has arrived! Time for fresh beginnings and clean code.");
        model.addAttribute("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy HH:mm:ss")));

        // List of available APIs
        List<ApiEndpoint> apiList = List.of(
                new ApiEndpoint("GET", "/api/greet/{name}", "Greeting API", "Returns a personalized welcome message"),
                new ApiEndpoint("GET", "/api/students", "Get All Students", "Retrieves complete list of students"),
                new ApiEndpoint("GET", "/api/students/{id}", "Get Student by ID", "Retrieves a specific student"),
                new ApiEndpoint("GET", "/api/students/search", "Search Students", "Search by name or surname"),
                new ApiEndpoint("POST", "/api/students", "Create Student", "Adds a new student (ADMIN only)"),
                new ApiEndpoint("PUT", "/api/students/{id}", "Update Student", "Updates existing student (ADMIN only)"),
                new ApiEndpoint("DELETE", "/api/students/{id}", "Delete Student", "Removes a student (ADMIN only)")
        );

        model.addAttribute("apis", apiList);
        return "index";
    }

    // ORIGINAL STUDENTS PAGE - keeps the original /students URL
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", service.findAll());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    // Inner class for API endpoints
    public static class ApiEndpoint {
        private final String method;
        private final String url;
        private final String title;
        private final String description;

        public ApiEndpoint(String method, String url, String title, String description) {
            this.method = method;
            this.url = url;
            this.title = title;
            this.description = description;
        }

        public String getMethod() { return method; }
        public String getUrl() { return url; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
    }
}
