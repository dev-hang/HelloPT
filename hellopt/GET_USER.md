# 로그인한 유저 정보 얻기

## Bean에서 가져오기

<pre>
<code>
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
UserDetails userDetails = (UserDetails)principal; 
// 유저 ID
String username = principal.getUsername(); 
// 유저 Password
String password = principal.getPassword();
</code>
</pre>

## Controller에서 가져오기
메서드 파라미터에 Principal을 추가한 후 사용
<pre>
<code>
@Controller 
public class SecurityController { 
    @GetMapping("/username")
    @ResponseBody 
    public String currentUserName(Principal principal) {
        // 유저 ID
        return principal.getName(); } 
    }
</code>
</pre>

Authentication 객체 사용
<pre>
<code>
@Controller 
public class SecurityController {
    @GetMapping("/username") 
    @ResponseBody 
    public String currentUserName(Authentication authentication) { 
        UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
        return userDetails.getUsername(); 
    }
}
</code>
</pre>

## @AuthenticationPrincipal
Spring Security에서 유저 인증 시 필요한 정보를 UserDetail에 저장

유저 ID, PW외에 필요한 부분이 있는 경우 UserDeails를 상속한 객체를 구현

HelloPT에서는 com.bit.hellopt.vo.CustomUserDetail이 UserDetail을 상속하여 구현함

- 데이터베이스에서 데이터를 받아와 com.bit.hellopt.vo.User 객체에 저장
- 받아온 User를 사용해 인증시 사용하는 객체인 CustomUserDetail을 초기화
- 키, 나이 등 필요한 정보가 있으면 CustomUserDetail에 필드 선언 한 후 생성자에서 값 초기화 할 것

<pre>
<code>
@Controller 
public class SecurityController {
    @GetMapping("/messages/inbox") 
    public ModelAndView currentUserName(@AuthenticationPrincipal CustomUserDetail customUser) { 
        String username = CustomUserDetail.getUsername(); 
        // .. find messages for this user and return them ... 
    } 
}
</code>
</pre>

## JSP

pom.xml에 Srping Security Tag lib를 사용하기 위해 의존성 추가

<pre>
<code>
    &ltdependency&gt
        &ltgroupId&gtorg.springframework.security&lt/groupId&gt
        &ltartifactId&gspring-security-taglibs&lt/artifactId&gt
        &ltversion&gt5.0.7.RELEASE&lt/version&gt
    &lt/dependency&gt
</code>
</pre>

JSP에 Spring Security Tag lib 추가
<pre>
<code>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
</code>
</pre>

Principal(로그인한 유저) 사용

<pre>
<code>
// isAuthenticated() - 로그인한 경우
// !isAuthenticated() - 로그인하지 않은 경우
&ltsecurity:authorize access="isAuthenticated()"&gt
    authenticated as
    //로그인한 유저 ID 표시
    &ltsecurity:authentication property="principal.username" /&gt 
&lt/security:authorize&gt
</code>
</pre>




