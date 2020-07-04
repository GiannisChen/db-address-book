package com.example.dbdemo;

import com.example.dbdemo.repository.RecordRepository;
import com.example.dbdemo.service.MyContactService;
import com.example.dbdemo.service.MyUsersService;
import com.example.dbdemo.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DbdemoApplicationTests {
    @Resource
    private MyContactService myContactService;
    @Resource
    private MyUsersService myUsersService;
    @Resource
    private RecordService recordService;
    @Test
    public void testRepository() {
        System.out.println("count: " + recordService.count());
        System.out.println("count: " + myUsersService.count());
    }

    @Test
    void contextLoads() {
    }

    @Resource
    private JdbcTemplate jdbcTemplate;
    //mysql简单测试
    @Test
    public void mySqlTest() {
        String sql = "select * from contact";
        List<MyContact> contactList = (List<MyContact>) jdbcTemplate.query(sql, new RowMapper<MyContact>() {
            @Override
            public MyContact mapRow(ResultSet resultSet, int row) throws SQLException {
                MyContact contact = new MyContact();
                contact.setAddress(resultSet.getString("address"));
                contact.setName(resultSet.getString("name"));
                contact.setMobile(resultSet.getString("mobile"));
                contact.setEmail(resultSet.getString("email"));
                contact.setQQ(resultSet.getString("QQ"));

                return contact;
            }
        });
        System.out.println("查询成功：");

        for(MyContact contact:contactList) {
            System.out.println(	"email: " + contact.getEmail() +
                    "\tname: " + contact.getName() +
                    "\tmobile: " + contact.getMobile() +
                    "\tQQ: " + contact.getQQ() +
                    "\taddress: " + contact.getAddress());
        }
    }
}
