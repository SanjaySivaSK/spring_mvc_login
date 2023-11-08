package com.codewithsanjay.Controller;

import com.codewithsanjay.Dao.StudentDao;
import com.codewithsanjay.Model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Homecontroller {

    @RequestMapping("/")
    public String showpage() {

        return "index";
    }

    @RequestMapping("/home")
    public String login(HttpServletRequest req, Model model) throws SQLException, ClassNotFoundException {


        String Firstname = req.getParameter("Firstname");
        String Lastname = req.getParameter("Lastname");
        StudentDao studentDao = new StudentDao();
        Student LoggedInUser = studentDao.loginuser(Firstname, Lastname);

//      for(Student student:students){
//           System.out.println(student.getFirstName());
//       }


       if (LoggedInUser != null) {
            List<Student> students = studentDao.getstudent();
           for(Student student:students){
               System.out.println(student.getFirstName());
           }
           model.addAttribute("students", students);
           return "home";
       } else {
            return "Register";
        }




    }

    @RequestMapping("/Register")
    public String showReg() {

        return "Register";
    }

    @RequestMapping("/FormRegister")
    public String showRegister(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        StudentDao studentDao = new StudentDao();
        String Firstname = req.getParameter("Firstname");
        String Lastname = req.getParameter("Lastname");
        String Country = req.getParameter("Country");
        String Language = req.getParameter("Language");
        String OS = req.getParameter("OS");
        studentDao.NewUser(Firstname, Lastname, Country, Language, OS);
        System.out.println(Firstname);
        System.out.println(Language);

        return "index";
    }



}


//    @RequestMapping("/processForm")
//     public String processForm(){
//        return "processForm";
//    }
//    @RequestMapping("/showForm")
//    public String showForm(){
//        return "showForm";
//    }
//
//    @RequestMapping("/processFormtwo")
//    public String processFormtwo(HttpServletRequest req, Model model){
//        String name = req.getParameter("name").toUpperCase();
//        model.addAttribute("message",name);
//        return "processForm";
//    }
//
//    @RequestMapping("/processFormthree")
//    public String processFormthree(@RequestParam ("name") String name, Model model){
//
//        model.addAttribute("message",name);
//        return "processForm";
//    }

