package springFw.ex08.mybatis.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springFw.ex08.mybatis.hr.model.EmpVO;
import springFw.ex08.mybatis.hr.service.IEmpService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * packageName    : springFW.ex06.mvc02.controller
 * fileName       : EmpController
 * author         : Yeong-Huns
 * date           : 2024-06-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-03        Yeong-Huns       최초 생성
 */
@Controller
public class EmpController {
    @Autowired
    IEmpService empService;

    @RequestMapping(value = "/hr/count")
    public String empCount(@RequestParam(value = "deptId", required = false, defaultValue = "0") int deptId,
        Locale locale , Model model){
        if(deptId == 0){
            model.addAttribute("count", empService.getEmpCount());
        }else {
            model.addAttribute("count", empService.getEmpCount(deptId));
        }
        return "hr/count";
    }

    @RequestMapping(value = "/hr/list")
    public String getAllEmps(Model model){
        List<EmpVO> empList = empService.getEmpList();
        model.addAttribute("empList", empList);
        return "hr/list";
    }

    @RequestMapping(value = "/hr/{empId}")
    public String getEmpInfo(@PathVariable int empId , Model model){
        EmpVO emp = empService.getEmpInfo(empId);
        model.addAttribute("emp", emp);
        return "hr/view";
    }

    @RequestMapping(value="/hr/insert" , method = RequestMethod.GET)
    public String insertEmp(Model model){
        model.addAttribute("jobList", empService.getAllJobId());
        model.addAttribute("managerList", empService.getAllManagerId());
        model.addAttribute("deptList", empService.getAllDeptId());
        return "hr/insertform";
    }

    @RequestMapping(value = "/hr/insert", method = RequestMethod.POST)
    public String insertEmp(EmpVO emp, RedirectAttributes redirectAttributes){
        try{
            empService.insertEmp(emp);
            redirectAttributes.addFlashAttribute("message", emp.getEmployeeId()+ "번 사원정보가 입력되었습니다.");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/hr/list";
    }
    @RequestMapping(value = "hr/update", method = RequestMethod.GET)
    public String updateEmp(int empId , Model model){
        model.addAttribute("emp", empService.getEmpInfo(empId));
        model.addAttribute("jobList", empService.getAllJobId());
        model.addAttribute("managerList", empService.getAllManagerId());
        model.addAttribute("deptList", empService.getAllDeptId());
        return "hr/updateform";
    }
    @RequestMapping(value = "/hr/update", method = RequestMethod.POST)
    public String updateEmp(EmpVO emp, RedirectAttributes redirectAttributes){
        try{
            empService.updateEmp(emp);
            redirectAttributes.addFlashAttribute("message", emp.getEmployeeId()+ "번 사원정보가 변경되었습니다.");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/hr/" + emp.getEmployeeId();
    }
    @RequestMapping(value = "hr/delete", method = RequestMethod.GET)
    public String deleteEmp(int empId, Model model){
        model.addAttribute("emp", empService.getEmpInfo(empId));
        return "hr/deleteform";
    }

    @RequestMapping(value = "hr/delete", method = RequestMethod.POST)
    public String deleteEmp(int empId , String email, Model model, RedirectAttributes redirectAttributes){
        try {
            int deleteRow = empService.deleteEmp(empId, email);
            if(deleteRow > 0){
                redirectAttributes.addFlashAttribute("message", empId + "번 사원정보가 삭제되었습니다.");
                return "redirect:/hr/list";
            }else {
                model.addAttribute("emp", empService.getEmpInfo(empId));
                model.addAttribute("message", "사번 또는 이메일 주소가 다릅니다.");
                return "hr/deleteform";
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/hr/list";
        }
    }

    @ExceptionHandler({SQLException.class, RuntimeException.class, IOException.class, NoSuchMethodException.class, ClassNotFoundException.class, InstantiationException.class, IllegalAccessException.class, Exception.class })
    public String runtimeException(HttpServletRequest request, Exception e, Model model){
        model.addAttribute("url", request.getRequestURI());
        model.addAttribute("exception", e);

        return "error/runtime";
    }

    @RequestMapping(value = "hr/json")
    @ResponseBody
    public List<EmpVO> getEmpJsonList(){
        List<EmpVO> empList = empService.getEmpList();
        return empList;
    }

    @RequestMapping(value = "hr/json/{empId}")
    @ResponseBody
    public EmpVO getEmpJson(@PathVariable int empId){
        EmpVO empList = empService.getEmpInfo(empId);
        return empList;
    }


}
