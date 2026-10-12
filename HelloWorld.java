package com.example.backend_4;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/* 注解必须贴在它所修饰对象的正上方
 * 这是个网络接口类的注解，其实是@Controller + @ResponseBody两个注解的合体
 * 这个注解是来告诉Spring这个类是控制器，方法返回的内容会直接作为响应返回给前端，所以它贴在 public 外面
 * 方法返回的内容就是下面return的内容，Spring会自动把这些内容放进HTTP响应里
 * 返回前端就是显示在浏览器界面上
 */
@RestController
public class HelloWorld {

    /*这是给方法的注解，说明这个方法的唯一网络入口是 /hello，并且只接受 GET 请求。
     *因为一个控制器类里可能有几十个方法,这样注释了过后，当浏览器发来请求时，程序就知道应该执行哪一个方法
     */
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Hello World");
        return "Hello World";
    }

    @PostMapping("/hello")
    public String sayPostHello() {
        System.out.println("Hello POST World");
        return "Hello POST World";
    }

    /* 路径要加/json，防止和之前的路径冲突而报错
     * 之前之所以不会冲突，是因为Spring框架接收请求的时候不单单依靠路径，而是靠路径+请求方式来找方法
     * 之前的Get和Post是不同的请求方式
     * 但现在如果不改路径的话，就是相同的路径加上相同的请求方式，很明显就要冲突了
     */
    @GetMapping("/hello/json")
    public Map<String, String> sayHelloJson() {
        return Map.of("message", "Hello World"); // Map.of造出来的Map不可变，不能往里面新增键值对，不能修改里面的值，不能删除里面的数据
    }

    @PostMapping("/hello/json")
    public Map<String, String> sayPostHelloJson() {
        return Map.of("message", "Hello POST World");
    }

    @GetMapping("/hello/name")
    public String sayHelloWithName(@RequestParam String name) { // 有了@RequestParam这个注解，如果前端请求里没带 name 这个参数，Spring 会直接返回 400 错误，而不是执行方法
        return "Hello，" + name;
    }

    @PostMapping("/hello/name")
    public String sayNiceWithName(@RequestParam String name) {
        return name + "，nice to meet you!";
    }

    /* 只POST而不GET是因为GET基本上不使用请求体（Body）
     * GET请求把参数拼在URL后面
     * 请求体是数据储存的地方，POST请求可以把数据放在请求体里，就不用把参数直接拼在URL后面
     * 可以防止直接跟在URL后面的数据太长让URL显得过长或者数据暴露在URL上
     */
    @PostMapping("/hello/name/json")
    public Map<String, String> sayNiceWithNameJson(@RequestBody Map<String, String> payload) {

        String name = payload.get("name"); // 从Map里把"name"值拿出来赋给name变量

        /* "name"代表JSON里的键
         *name是java变量，是从前端请求体里面提取出来的值xxx
         * 逗号前面是键，后面是值，加号是在拼接内容
         */
        return Map.of("name", name + "，nice to meet you!");

    }

}
