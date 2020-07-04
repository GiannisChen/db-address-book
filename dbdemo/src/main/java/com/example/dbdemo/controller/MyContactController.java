package com.example.dbdemo.controller;

import com.example.dbdemo.*;
import com.example.dbdemo.service.MyContactService;
import com.example.dbdemo.service.MyUsersService;
import com.example.dbdemo.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyContactController {
    @Resource
    private MyContactService myContactService;
    @Resource
    private RecordService recordService;
    @Resource
    private MyUsersService myUsersService;

    @RequestMapping({"","/"})
    public String index() {
        return "login";
    }

    //基础测试
    @RequestMapping("myContact/test")
    public String test(Model model) {
        List<MyContact> myContacts = myContactService.findAll();
        for(MyContact contact:myContacts) {
            System.out.println(	"email: " + contact.getEmail() +
                    "\tname: " + contact.getName() +
                    "\tmobile: " + contact.getMobile() +
                    "\tQQ: " + contact.getQQ() +
                    "\taddress: " + contact.getAddress());
        }
        model.addAttribute("contacts",myContacts);
        return "myContact";
    }

    @RequestMapping("pageTest")
    public String pageTest() {
        return "pageTest";
    }

    @RequestMapping("/statics")
    public String statics(Model model) {
        List<AddressStatic> addressStatics = myContactService.findAllAddress();

        List<String> addresses = new ArrayList<>();
        List<Long> counts = new ArrayList<>();
        List<Echarts> echarts = new ArrayList<>();
        Long sum = 0L;
        for(AddressStatic a:addressStatics) {
            addresses.add(a.getAddress());
            counts.add(a.getCount());
            sum += a.getCount();
            echarts.add(new Echarts((a.getCount()).intValue(),a.getAddress()));
        }

        System.out.println("hello");

        long a = recordService.count();
        long b = myUsersService.count();

        System.out.println("count: " + a);
        System.out.println("count: " + b);

        System.out.println("hello");

        model.addAttribute("ymax",sum);
        model.addAttribute("countRecords",recordService.count());
        model.addAttribute("countUsers",myUsersService.count());
        model.addAttribute("addresses",addresses);
        model.addAttribute("counts",counts);
        model.addAttribute("data",echarts);
        return "statics";
    }

    @RequestMapping("record")
    public String record() {
        return "record";
    }

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("warning")
    public String warning() {
        return "warning";
    }

    @PostMapping("/register/submit")
    public String registerSubmit(@RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "mobile", required = false) String mobile,
                                 @RequestParam(value = "password", required = false) String password,
                                 @RequestParam(value = "passwords", required = false) String passwords,
                                 Model model) {

        System.out.println("in");
        List<MyUsers> n = myUsersService.findByUsername(username);
        List<MyUsers> m = myUsersService.findByMobile(mobile);

        System.out.println(n.size() + "  " + m.size());

        if(password.equals(passwords) && n.isEmpty() && m.isEmpty()) {
            MyUsers myUsers = new MyUsers();
            myUsers.setMobile(mobile);
            myUsers.setUsername(username);
            myUsers.setPassword(password);
            myUsersService.save(myUsers);

            System.out.println("if");

            return "redirect:/";
        }
        else {
            System.out.println("else");
            return "redirect:/warning";
        }
    }
}
