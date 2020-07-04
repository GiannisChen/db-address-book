package com.example.dbdemo.controller;


import com.example.dbdemo.*;
import com.example.dbdemo.service.MyContactService;
import com.example.dbdemo.service.MyUsersService;
import com.example.dbdemo.service.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MyIndexController {

    @Resource
    private MyContactService myContactService;
    @Resource
    private MyUsersService myUsersService;
    @Resource
    private RecordService recordService;


    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        Map<String, Object> map) {
        System.out.println(username);
        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            List<MyUsers> myUsersList = myUsersService.findByUsername(username);
            if(!myUsersList.isEmpty()) {
                System.out.println(myUsersList.get(0).getPassword() + " " + password);
                if(myUsersList.get(0).getPassword().equals(password)) {
                    model.addAttribute("mobile", myUsersList.get(0).getMobile());
                    System.out.println(myUsersList.get(0).getMobile());

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

                    model.addAttribute("countRecords",recordService.count());
                    model.addAttribute("countUsers",myUsersService.count());
                    model.addAttribute("ymax",sum);
                    model.addAttribute("addresses",addresses);
                    model.addAttribute("counts",counts);
                    model.addAttribute("data",echarts);
                    return "statics";
                }
            }
            map.put("msg","用户名密码错误");
            return "login";
        }
        else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
//    public String login(Model model) {
//        model.getAttribute("username");
//        return "login";
//    }

    @RequestMapping("/home")
    public String home(Model model) {

        Pageable pageable = PageRequest.of(0,7);
        Page<MyContact> contactsPage = myContactService.findAll(pageable);
        model.addAttribute("contactPage",contactsPage);
        model.addAttribute("pageNum",contactsPage.getTotalPages());

        return "home";
    }

    @RequestMapping("/home/index")
    public String homeIndex(Model model,
                           @RequestParam(value = "i", required = false)Integer i) {

        if(i == null) {
            i = 1;
        }
        Pageable pageable = PageRequest.of(i - 1,7);
        Page<MyContact> contactsPage = myContactService.findAll(pageable);
        model.addAttribute("contactPage",contactsPage);
        model.addAttribute("pageNum",contactsPage.getTotalPages());

        return "home";
    }

//    //修改和回显功能
//    @RequestMapping("/updateContact")
//    public String updateContact(Model model, int id) {
//        //System.out.println(id);
//        Optional<MyContact> myContact = myContactService.findById(id);
//        //System.out.println(myContact.get().getName());
//        model.addAttribute("contact",myContact.get());
//        return "updateContact";
//    }

    //增加功能
    @RequestMapping("/addContact")
    public String addContact() {
        System.out.println("add");
        return "addContact";
    }
    @PostMapping("/addContact/submit")
    public String addContactSubmit(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "mobile", required = false) String mobile,
                                   @RequestParam(value = "address", required = false) String address,
                                   @RequestParam(value = "QQ", required = false) String QQ,
                                   @RequestParam(value = "email", required = false) String email,
                                   Map<String, Object> map) {
        System.out.println("this is add submit");
        if(name == null && mobile == null) {
            System.out.println("fuck null");
            map.put("msg","未知错误!");
            return "home";
        }
        else {
            MyContact myContact = new MyContact();
            myContact.setName(name);
            myContact.setMobile(mobile);
            myContact.setAddress(address);
            myContact.setQQ(QQ);
            myContact.setEmail(email);
            System.out.println(myContact.getName());
            myContactService.save(myContact);
            return "redirect:/home";
        }
    }

    //更新功能(dropped)
    @PostMapping("/updateContact/submit")
    public String updateContactSubmit(int id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "mobile", required = false) String mobile,
                               @RequestParam(value = "address", required = false) String address,
                               @RequestParam(value = "QQ", required = false) String QQ,
                               @RequestParam(value = "email", required = false) String email,
                               Map<String, Object> map) {
        System.out.println(id);
        System.out.println(name);
        if(id < 0 && name == null && mobile == null) {
            System.out.println("fuck null");
            map.put("msg","未知错误!");
            return "home";
        }
        else {
            MyContact myContact = new MyContact();
            myContact.setId(id);
            myContact.setName(name);
            myContact.setMobile(mobile);
            myContact.setAddress(address);
            myContact.setQQ(QQ);
            myContact.setEmail(email);

            System.out.println(myContact.getName());

            myContactService.save(myContact);
            return "redirect:/home";
        }

    }

    //删除功能
    @RequestMapping("/delContact")
    public String deleteContact(int id) {
        myContactService.delete(id);
        return "redirect:/home";
    }

    //查找功能
    @RequestMapping("/search")
    public String searchByName(@RequestParam(value = "searchName", required = false)String searchName,
                               @RequestParam(value = "i", required = false)Integer i,
                               Model model) {

        System.out.println("hello");
        if(i == null) {
            i = 1;
        }

        if(StringUtils.isEmpty(searchName)) {
            Pageable pageable = PageRequest.of(i - 1,7);
            Page<MyContact> contactsPage = myContactService.findAll(pageable);
            model.addAttribute("contactPage",contactsPage);
            model.addAttribute("pageNum",contactsPage.getTotalPages());
        }
        else {
            System.out.println(searchName);
            List<MyContact> myContactList;
            myContactList = myContactService.findByNameLike("%" + searchName + "%");
            System.out.println(myContactList.size());
            model.addAttribute("contacts",myContactList);
        }
        return "home";
    }


    @RequestMapping("/contact/findALL")
    @ResponseBody
    public List<MyContact> contactFindAll(){
        System.out.println("hello");
        List<MyContact> list = myContactService.findAll();
        return list;
    }

    @RequestMapping("/record/findALL")
    @ResponseBody
    public List<CallRecords> recordFindAll(){

        System.out.println("hello");
        List<CallRecords> list = recordService.findAll();
        return list;
    }

    @RequestMapping("/record/find/{mobile}")
    @ResponseBody
    public List<CallRecords> recordFindAll(@PathVariable("mobile")String mobile){

        if(mobile == null) {
            System.out.println("mobile null");
        }
        else {
            System.out.println(mobile);
        }
        System.out.println("hello");
        List<CallRecords> listFrom = recordService.findByFrom(mobile);
        List<CallRecords> listTo = recordService.findByTo(mobile);
        List<CallRecords> list = new ArrayList<>();

        list.addAll(listFrom);
        list.addAll(listTo);

        System.out.println(listFrom.size() + "  " + listTo.size());
        return list;
    }

    @RequestMapping("/updateContact/{id}")
    public String updateContact(Model model, @PathVariable("id")int id) {
        System.out.println(id);
        Optional<MyContact> myContact = myContactService.findById(id);
        //System.out.println(myContact.get().getName());
        model.addAttribute("contact",myContact.get());
        return "updateContact";
    }

    @RequestMapping("/delContact/{id}")
    public String delContact(Model model, @PathVariable("id")int id) {
        myContactService.delete(id);
        return "redirect:/home";
    }

    @RequestMapping("/delRecord/{id}")
    public String delRecord(Model model, @PathVariable("id")int id) {
        recordService.delete(id);
        return "redirect:/record";
    }

    @RequestMapping("/addRecord")
    public String addRecord() {
        System.out.println("add");
        return "addRecord";
    }

    @PostMapping("/addRecord/submit")
    public String addRecordSubmit(@RequestParam(value = "from", required = false) String from,
                                   @RequestParam(value = "to", required = false) String to,
                                   @RequestParam(value = "duration", required = false) String duration,
                                   @RequestParam(value = "date", required = false) String date,
                                   Map<String, Object> map) {
        System.out.println("this is add submit");

        System.out.println(from);
        System.out.println(to);
        System.out.println(duration);
        System.out.println(date);

        Time time = Time.valueOf(duration);
        System.out.println("duration" + time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = sdf.getCalendar();

        System.out.println("date" + calendar);

        CallRecords callRecords = new CallRecords();
        callRecords.setFrom(from);
        callRecords.setTo(to);
        //callRecords.setDuration(time);
        //callRecords.setDate(calendar);

        recordService.save(callRecords);

        return "redirect:/record";

    }
}
