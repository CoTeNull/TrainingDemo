package cn.cote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @RequestMapping 组合注解 RequestBody与Controller(Spring4新特性)
@RestController
public class Hello {

    //将配置文件值注入到下面的对象中
//    @Value("${IQ}")
//    private String IQ;
//
//    @Value("${content}")
//    private String content;

    //引用模型类(配置)
    @Autowired
    private PeoplePOJO peoplePOJO;
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)//可以设置多个路由名称
    public String sayHello(){
        return "吕胜昆的智商等级为:"+peoplePOJO.getIQ()+"级别";
    }

    @RequestMapping(value = "/number/{id}",method = RequestMethod.GET)
    public String sayHello2(@PathVariable(value = "id",required = false) int id){ //直接将数据映射到新建id中
        return "id: "+ id;
    }
 ////GET:::  ----下面注解可以用@GetMapping(value="/number2")代替
    @RequestMapping(value = "/number2",method = RequestMethod.GET)
    public String sayHello3(@RequestParam(value = "id",required = false,defaultValue = "0") int id){
        return "id: "+ id;
    }
    //GET参数的选择传入和默认值：
    //设置传入值为id          是不是必须传入的值     默认值为多少
    //@RequestParam(value = "id",required = false, defaultValue = "0") int id;

    //也可以在方法里实例化一个模型，将服务器对应键值的数据填入模型内如
//    public String sayHello2(User user){ //直接将数据映射到新建id中
//        return user.get("username");
//    }
    @Autowired
    private PeoPleRepository peoPleRepository;

    @GetMapping("/show")
    public List<people> sayHello3(){
        return peoPleRepository.findAll(); //返回所有的数据
    }

    @PostMapping("/add")
    public people sayHello4(@RequestParam("iq") String iq, @RequestParam("eq") Integer eq){
        System.out.println(iq);
        people this_people=new people();
        this_people.setIq(iq);
        this_people.setEq(eq);
        return peoPleRepository.save(this_people); //存数据
        //findOne() 通过ID查询 可在接口中自定义查询;
    }

    @GetMapping("/findByIq/{iq}")
    public List<people> sayHello5(@PathVariable(value = "iq") String iq){

        return peoPleRepository.findByIq(iq); //返回所有的数据
    }

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/two")
    public void peopleTwo(){
        peopleService.insertTwo();
    }
}
