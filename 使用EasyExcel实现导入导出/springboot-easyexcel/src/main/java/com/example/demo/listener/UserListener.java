package com.example.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@SuppressWarnings("ALL")
@Component
@Scope("prototype")	// 作者要求每次读取都要使用新的Listener
public class UserListener extends AnalysisEventListener<User> {
    private UserService userService;
    private Boolean updateSupport;
    private String operName;

    private Set<User> juliSet_fail = new HashSet<>(); // 插入失败的数据

    private int success = 0; // 统计插入成功的条数
    private int fail = 0; // 统计插入失败的条数

    // 获取插入失败的数据
    public Set<User> getSet_fail(){
        return juliSet_fail;
    }

    // 获取插入成功的条数
    public int getSuccess(){
        return success;
    }

    // 获取插入失败的条数
    public int getFail(){
        return fail;
    }

    /**
     *
     * @param userService
     * @param updateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作人
     */
    public UserListener(UserService userService, Boolean updateSupport, String operName){
        this.userService = userService;
        this.updateSupport = updateSupport;
        this.operName = operName;
    }

    @Getter
    @Setter
    private List<User> list = new ArrayList<>();


    /**
     * 读的时候，每读取excel一行记录就会调用监听器的invoke()方法
     * @param user
     * @param analysisContext
     */
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {

        //从excel中读取的数据都在invoke中操作
        System.out.println("读的时候，每读取excel一行记录就会调用监听器的invoke()方法");
        //通过excel中的id去数据库查找 数据 如果存在则更新 如果不存在 添加到数据库
        list.add(user);
        User user_new = userService.queryUserById(user.getId());
        if (updateSupport){ // 更新支持，如果已存在，则进行更新数据
            if (user_new != null){ // 如果要插入的数据已存在
                boolean update = userService.updateUser(user);
                if (update){
                    ++success;
                }else {
                    juliSet_fail.add(user);
                    ++fail;
                }
            }else { // 如果要插入的数据不存在
                boolean save = userService.addUser(user);
                Integer id = user.getId();
                System.out.println("-----------");
                System.out.println(id);
                System.out.println("-----------");
                if (save){ // 插入成功
                    ++ success;
                }else {// 插入失败
                    juliSet_fail.add(user);
                    ++fail;
                }
            }
        }else {
            if (user_new != null) { // 如果要插入的数据已存在
                juliSet_fail.add(user_new);
            }else {
                boolean save = userService.addUser(user);
                if (save) {// 插入成功
                    ++success;
                } else {// 插入失败
                    juliSet_fail.add(user);
                    ++fail;
                }
            }
        }

    }

    /**
     * 整个过程结束后执行该方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("整个过程结束后执行该方法");
    }


    /**
     * 读取excel表头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息"+headMap);
    }
}
