package cn.cote.shiro;


import cn.cote.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


public class ShiroTest {

    public static void main(String[] args) {
      Md5Hash md5Hash = new Md5Hash("123456","CoTe");
        System.out.println(md5Hash);
    }

    @Test
    public void shiro6(){

        CustomRealm customRealm = new CustomRealm();

        //构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //处理经过md5加密的用户信息
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);//加密次数
        customRealm.setCredentialsMatcher(matcher);

        //主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //需要认证的信息
        UsernamePasswordToken token= new UsernamePasswordToken("admin","123456");
        subject.login(token);

        subject.checkRole("admin");
        subject.checkPermissions("user:add","user:delete");
        System.out.println("是否成功:"+subject.isAuthenticated());
    }
}
