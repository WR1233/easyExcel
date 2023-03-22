package com.example.demo.controller;


import com.example.demo.pojo.User;
import com.example.demo.pojo.query.UserQuery;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 导入本地用户userId与聚力对应userId
     * @param file 导入文件
     * @param updateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 是否导入成功
     * @throws Exception
     */
    @PostMapping("/importJuLiData")
    @ResponseBody
    public Boolean importJuLiData(@RequestParam MultipartFile file, @RequestParam boolean updateSupport) throws Exception
    {
        String operName = "fengzihao"; // 操作人
        Boolean flag = userService.importJuLiData(file, updateSupport, operName);
        return flag;
    }


    /**
     * 导出本地用户userId与聚力对应userId
     * @return 是否导出成功
     * @throws Exception
     */
    @GetMapping("/exportJuLiData")
    @ResponseBody
    public void exportJuLiData(HttpServletResponse response) throws Exception
    {
        String operName = "fengzihao"; // 操作人
        userService.exportJuLiData(response, operName);
    }

    @GetMapping("/")
    public String index(Model model, UserQuery userQuery) {
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        model.addAttribute("page", userPageInfo);
        return "index";
    }

    @PostMapping("/")
    public String listUserByName(Model model, UserQuery userQuery) {
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        model.addAttribute("page", userPageInfo);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        boolean b = userService.deleteUserById(id);
        if (b) {
            attributes.addFlashAttribute("message", "删除用户成功");
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("message", "删除用户失败");
            return "redirect:/";
        }
    }


    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.queryUserById(id));
        return "editUser";
    }


    @PostMapping("/edit")
    public String edit(User user, RedirectAttributes attributes) {
        int i = userService.selectUserByName(user.getName());
        /* 根据id的是否为null判断新增还是修改操作，null为新增，不为null为修改 */
        Integer id = user.getId();
        /* 修改用户 */
        if (id != null) {
            /* 用户名不存在 */
            if (i == 0) {
                boolean b = userService.updateUser(user);
                if (b) {
                    attributes.addFlashAttribute("message", " 更新用户成功");
                    return "redirect:/";
                } else {
                    attributes.addFlashAttribute("message", "更新用户失败");
                    return "redirect:/";
                }
            } else {
                attributes.addFlashAttribute("message", "该用户名已存在");
                return "redirect:/edit/" + user.getId();
            }
        // 新增用户
        } else {
            if (i == 0) {
                boolean b = userService.addUser(user);
                if (b) {
                    attributes.addFlashAttribute("message", " 新增用户成功");
                    return "redirect:/";
                } else {
                    attributes.addFlashAttribute("message", "新增用户失败");
                    return "redirect:/";
                }
            } else {
                attributes.addFlashAttribute("message", "该用户名已存在");
                return "redirect:/";
            }
        }
    }

    /* 新增操作 */
    @GetMapping("/update")
    public String toUpdate(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/insert")
    @ResponseBody
    public int insert(@RequestBody User user) {
        return userService.insert(user);
    }


    /**
     * 使用easyexcel实现组合项目的导入
     */

    @PostMapping("/importCompositeItemData")
    @ResponseBody
    public Boolean importCompositeItemData(@RequestParam MultipartFile file, @RequestParam boolean updateSupport) throws Exception
    {
        String operName = "wr"; // 操作人
        Boolean flag = userService.importCompositeItemData(file, updateSupport, operName);
        return flag;
    }

}
