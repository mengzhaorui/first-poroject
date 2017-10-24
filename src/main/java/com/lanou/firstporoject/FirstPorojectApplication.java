package com.lanou.firstporoject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class FirstPorojectApplication {
	public static List<Map<String,Object>> li=new ArrayList();

	public static void main(String[] args) {

		SpringApplication.run(FirstPorojectApplication.class, args);


	}
	@RequestMapping("helloWord")
	@ResponseBody
	public  String helloWord(String name){
		return "屌丝"+name+"你好";
	}
	@RequestMapping("helloWord1")
	public ModelAndView hellowSpringBoot(String name, String password){
		ModelAndView modelAndView = new ModelAndView("hello");
		List<Map<String,Object>> list = new ArrayList<>();
		for (int i = 0; i<10; i++){
			Map<String,Object>person=new HashMap<>();
			person.put("name","张三"+i+1);
			person.put("age",10+i+1);
			list.add(person);

		}
		modelAndView.addObject("username",name);
		modelAndView.addObject("userpassword",password);
		modelAndView.addObject("list",list);
		for (Object o:list){
			System.out.println(o);

		}
		return  modelAndView;
	}
	@RequestMapping("regusr")
	private  ModelAndView hello(String name, String pwd){
		ModelAndView modelAndView = new ModelAndView( "regist");
		return modelAndView;
	}
	@RequestMapping("REGUSR")
	private  ModelAndView  REGUSR(HttpServletRequest request , @RequestParam("acc") String acc, @RequestParam("pwd") String pwd){
		ModelAndView modelAndView =new ModelAndView("login");
		Map<String,Object> person =new HashMap<>();
		person.put("acc",acc);
		person.put("pwd",pwd);
		li.add(person);
		return modelAndView;
	}
	@RequestMapping("lO")
	public  ModelAndView  lOGIN(String acc, String pwd){
		ModelAndView modelAndView = new ModelAndView( "lOGIN");
		return  modelAndView;
	}
	@RequestMapping("lOGIN")
	@ResponseBody
	private  String lOGIN (HttpServletRequest request,@RequestParam(value = "acc") String acc,@RequestParam( value = "pwd") String pwd){
		Map<String ,Object>peron= new HashMap<>();
		peron.put("acc",acc);
		peron.put("pwd",pwd);
		boolean t=li.contains(peron);
		if(t){
			return "登陆成功!!";
		}else{
			return "账号或密码输入错误";
		}



		}


	@RequestMapping ("user")
	public  ModelAndView USER(String acc){
		ModelAndView modelAndView = new ModelAndView( "USER");
		if (acc==null){
			modelAndView.addObject("list",li);

		}else {
			List<Map<String,Object>>lis =new ArrayList<Map<String, Object>>();
			for (Map<String,Object>o:li){
				if (o.get("acc").equals(acc)){
					lis.add(o);
					modelAndView.addObject("list",lis);
				}

			}
		}
		return modelAndView;

	}

}

