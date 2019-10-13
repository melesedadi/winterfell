package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());

        return "index";
    }



    @GetMapping("/adddepartment")

    public String departmentForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }


    @PostMapping("/process")
    public String processDepartmentForm(@Valid Department department, BindingResult result){

        if (result.hasErrors()){
            return "departmentform"; }

        departmentRepository.save(department);

        return "redirect:/departmentlist";
    }


    @RequestMapping("/departmentlist")

    public String departmentList(Model model){

        model.addAttribute("departments", departmentRepository.findAll());

        return "departmentlist";
    }

   /* @RequestMapping("/departmentlist1")
    public String departmentList1(){
        return "departmentlist";
    }*/

    @GetMapping("/addemployee")

    public String employeeForm(Model model){

        model.addAttribute("departments", departmentRepository.findAll());

        model.addAttribute("employee", new Employee());

        return "employeeform";
    }


    @PostMapping("/processempoloyee")

    public String processEmployeeForm(@Valid Employee employee,  BindingResult result,@RequestParam("file") MultipartFile file){

        if (result.hasErrors()&&file.isEmpty()){

            return "/addemployee";
        }
       /* if (file.isEmpty()){
            return "redirect:/employeeform";
        }*/
        try {
            Map uploadResult =cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            employee.setHeadshot(uploadResult.get("url").toString());
            /*employeeRepository.save(employee);*/
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/employeeform";
        }
        employeeRepository.save(employee);

        return "redirect:/employeelist";
    }


    @RequestMapping("/employeelist")

    public String employeeList(Model model){

        model.addAttribute("employees", employeeRepository.findAll());

        return "employeelist";
    }


    @RequestMapping("/detail_department/{id}")

    public String showDepartment(@PathVariable("id") long id, Model model){

        model.addAttribute("department", departmentRepository.findById(id).get());
        return "showdepartment";
    }


    @RequestMapping("/update_department/{id}")

    public String updateDepartment(@PathVariable("id") long id, Model model){

        model.addAttribute("department", departmentRepository.findById(id).get());

        return "departmentform";
    }


    @RequestMapping("/delete_department/{id}")

    public String delDepartment(@PathVariable("id") long id){

        departmentRepository.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/detail_employee/{id}")

    public String showEmployee(@PathVariable("id") long id, Model model){

        model.addAttribute("employee", employeeRepository.findById(id).get());

        return "showemployee";

    }


    @RequestMapping("/update_employee/{id}")

    public String updateEmployee(@PathVariable("id") long id, Model model){

        model.addAttribute("employee", employeeRepository.findById(id).get());

        model.addAttribute("departments",departmentRepository.findAll());

        return "employeeform";

    }



    @RequestMapping("/delete_employee/{id}")

    public String delEmployee(@PathVariable("id") long id){

        employeeRepository.deleteById(id);

        return "redirect:/";

    }
}
