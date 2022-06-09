package com.linkface.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.linkface.dto.UserDTO;
import com.linkface.entity.FoodItem;
import com.linkface.entity.RecipeName;
import com.linkface.entity.UserData;
import com.linkface.security.AccessAccount;
import com.linkface.service.FoodItemsService;
import com.linkface.service.RecipeNameService;
import com.linkface.service.UserDataService;
import com.linkface.service.UserService;
import com.linkface.util.SessionActionHandler;

import lombok.Setter;

@CrossOrigin("*")
@Controller
public class SampleController {
	UserDTO test = new UserDTO();
	@Setter(onMethod_=@Autowired)
	private UserService userService;
	@Setter(onMethod_=@Autowired)
	private RecipeNameService service;
	@Setter(onMethod_=@Autowired)
	private UserDataService dataservice;
	@Setter(onMethod_=@Autowired)
	private FoodItemsService foodservice;
	
	
	@PostMapping("/react/t")
	public String facePost(@RequestBody UserDTO dto) {
		System.out.println(dto);
		if(dto != null) {
			SessionActionHandler sessionActionHandler = new SessionActionHandler();
			sessionActionHandler.CreateLoginSession(dto.getId(),dto.getPassword());
			dto = null;
		}
		return "/homepage";
		
	}
	@GetMapping("/react/t")
	public String faceGet() {
		System.out.println("window location get test");
		return "redirect:/homepage";
	}
	

	@GetMapping("/homepage")
	public void recipeGet(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
	      AccessAccount account = (AccessAccount) principal;
	      Long key = account.getUserInfo().getUserKey();
	      List<UserData> jjimdata = dataservice.getJJim(key);
	      List<RecipeName> list = service.getList();
	      List<RecipeName> jjimlist = new ArrayList<RecipeName>();
	      list.forEach(i->{
	    	  jjimdata.forEach(j->{
	    		  if(i.getRecipeid() == j.getJjim()){	
		    		  jjimlist.add(i);
	    		  }
	    	  });
	      });
	      List<Long> jjims = new ArrayList<Long>();
	      jjimdata.forEach(i-> jjims.add(i.getJjim()));
	      model.addAttribute("jjims",jjims);
		}
		List<RecipeName> list = service.getList();
		model.addAttribute("food",list);
		
	}

//	@PostMapping("/homepage")
//	public void recipePost() {
//
//	}
	
	@GetMapping("/choice")
	public void choiceGet() {
		
	}
	
	@GetMapping("/chuchun")
	public void chuchunGet(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
			Long key = account.getUserInfo().getUserKey();
			List<FoodItem> fooditemlist = foodservice.getFoodItems(); 
		    List<String> fooditems = new ArrayList<String>();
		    List<Long> jjimlist = new ArrayList<>();
		    List<Long> recipeids = new ArrayList<Long>();
		    dataservice.getJJim(key).forEach(i->{
		    	if(i.getFoodingredients() != null) {
		    		fooditems.add(i.getFoodingredients());
		    	}
		    });
		    dataservice.getJJim(key).forEach(i->{
		    	if(i.getJjim() != null) {
			    	jjimlist.add(i.getJjim());	
			    }
		    });
		    fooditemlist.forEach(i->{
		    	fooditems.forEach(j->{
		    		if(i.getIrdntnm().equals(j)) {
		    			recipeids.add(i.getRecipeid());
		    		}
		    	});
		    });
		    List<RecipeName> recipelist = service.getList();
		    List<RecipeName> myrecipelist = new ArrayList<RecipeName>();
		    List<RecipeName> chuchunlist = new ArrayList<RecipeName>();
		    List<RecipeName> chuchun2list= new ArrayList<RecipeName>();
		    fooditems.forEach(i->System.out.println(i));
		    recipelist.forEach(i->{
		    	jjimlist.forEach(j->{
		    		if(i.getRecipeid()==j) {
		    			myrecipelist.add(i);
		    		}
		    	});
		    });
		    recipelist.forEach(i->{
		    	myrecipelist.forEach(j->{
		    		if(i.getTynm().equals(j.getTynm()))
		    		{
		    			chuchunlist.add(i);
		    		}
		    		
		    	});
		    });
		    recipelist.forEach(i->{
		    	recipeids.forEach(j->{
		    		if(i.getRecipeid()==j) {
		    			chuchun2list.add(i);
		    		}
		    	});
		    });
		    model.addAttribute("chuchun1", chuchunlist.stream().distinct().collect(Collectors.toList()));
		    model.addAttribute("chuchun2", chuchun2list.stream().distinct().collect(Collectors.toList()));
		}
	}
	@PostMapping("/choice")
	public void myfooditemPost(UserData data) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
		    Long key = account.getUserInfo().getUserKey();
		    data.setUserKey(key);
		    System.out.println(data.getFoodingredients());

		    
		    
		    
		}
	}
	
	@GetMapping("/jjim")
	public void jjimGet(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
	      AccessAccount account = (AccessAccount) principal;
	      Long key = account.getUserInfo().getUserKey();
	      List<UserData> jjimdata = dataservice.getJJim(key);
	      List<RecipeName> list = service.getList();
	      List<RecipeName> jjimlist = new ArrayList<RecipeName>();
	      list.forEach(i->{
	    	  jjimdata.forEach(j->{
	    		  if(i.getRecipeid() == j.getJjim()){	
		    		  jjimlist.add(i);
	    		  }
	    	  });
	      });
	      model.addAttribute("jjim",jjimlist);
		}
	}
	
	@PostMapping(value="/regjjim" , produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<Long>> regjjimPost(@RequestBody UserData userdata) {
		System.out.println("등록처리");
		System.out.println(userdata);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(!principal.equals("anonymousUser")) {
		      AccessAccount account = (AccessAccount) principal;
		      Long key = account.getUserInfo().getUserKey();
		      UserData result = new UserData();
		      result.setUserKey(key);
		      result.setJjim(userdata.getJjim());
		      result.setFoodingredients(null);
		      dataservice.insertJJim(result);
		      userService.checkSessionAndUpdate(key);
		      List<UserData> jjimdata = dataservice.getJJim(key);
		      List<RecipeName> list = service.getList();
		      List<RecipeName> jjimlist = new ArrayList<RecipeName>();
		      list.forEach(i->{
		    	  jjimdata.forEach(j->{
		    		  if(i.getRecipeid() == j.getJjim()){	
			    		  jjimlist.add(i);
		    		  }
		    	  });
		      });
		      List<Long> jjims = new ArrayList<Long>();
		      jjimdata.forEach(i-> jjims.add(i.getJjim()));
		      System.out.println(jjims);
		      return new ResponseEntity<List<Long>>(jjims,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@PostMapping(value="/deljjim", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<Long>> deljjimPost(@RequestBody UserData userdata){
		System.out.println("삭제처리");
		System.out.println(userdata);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
		      AccessAccount account = (AccessAccount) principal;
		      Long key = account.getUserInfo().getUserKey();
		      UserData result = new UserData();
		      result.setUserKey(key);         
		      result.setJjim(userdata.getJjim());
		      result.setFoodingredients(null);
		      dataservice.deleteJJim(result);
		      userService.checkSessionAndUpdate(key);
		      List<UserData> jjimdata = dataservice.getJJim(key);
		      System.out.println(jjimdata);
		      List<RecipeName> list = service.getList();
		      List<RecipeName> jjimlist = new ArrayList<RecipeName>();
		      list.forEach(i->{
		    	  jjimdata.forEach(j->{
		    		  if(i.getRecipeid() == j.getJjim()){	
			    		  jjimlist.add(i);
		    		  }
		    	  });
		      });
		      List<Long> jjims = new ArrayList<Long>();
		      jjimdata.forEach(i-> jjims.add(i.getJjim()));
		      System.out.println(jjims);
		      return new ResponseEntity<List<Long>>(jjims,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/searchtest")
	public void tests() {
		
	}
	
	// 메인
	@GetMapping(value={"","/main"})
	public String main() {

		return "main";
	}

	
	// 회원
	@GetMapping("/member")
	public String member() {
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 AccessAccount account = (AccessAccount)principal;
		 System.out.println(account.getAuthorities());
		 return "redirect:/homepage";

	}
	
	// 관리자
	@GetMapping("/admin")
	public void admin() {
		
	}
	
	// 로그인
	@GetMapping("/login")
	public void login() {

	}
	@PostMapping("/login")
	public void loginPost() {

	}
	// 회원가입
	@GetMapping("/singup")
	public void singup() {
		
	}
	
	// 회원가입
	@PostMapping("/singup")
	public ResponseEntity<String> singupPost(@RequestBody UserDTO newUser) {
		// 상태값
		boolean status = true;
		// 백엔드에서 다시 중복체크와 패스워드 검증 
		status = userService.duplicateCheck(newUser,"all");
		status = newUser.getPassword().equals(newUser.getPasswordCheck());
		// DB 생성
		status = userService.registUser(newUser);
		// 로그인
		
		// 필요하다면 추후 추가
		
		
		// 위 로직 중 하나라도 실패할 경우 오류 반환
		return status ? 
				new ResponseEntity<>(newUser.getId(),HttpStatus.OK) :
				new ResponseEntity<>("BAD REQUEST",HttpStatus.BAD_REQUEST);
	}
	
	// ID EMAIL 증벅 체크
	@PostMapping("/duplicatecheck")
	public ResponseEntity<Boolean> Check(@RequestBody UserDTO checkObj) {
		
		// id 값이 있다면 id 검사 / null 이라면 email 검사
		return new ResponseEntity<>
			(checkObj.getId() != null ?
					  userService.duplicateCheck(checkObj,"id")
					: userService.duplicateCheck(checkObj,"email"),HttpStatus.OK);
	}
	
	// 권한없는 사용자
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	
}
