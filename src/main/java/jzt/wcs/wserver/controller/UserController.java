package jzt.wcs.wserver.controller;

import jzt.wcs.wserver.moudle.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/list")
	public String  listUser(Model model) {
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i <10; i++) {
			userList.add(new User(i,"张三"+i,"20"+i,"中国广州"));
		}

		model.addAttribute("users", userList);
		return "/user/list";
	}
}
