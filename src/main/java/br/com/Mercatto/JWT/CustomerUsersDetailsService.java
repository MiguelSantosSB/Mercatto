package br.com.Mercatto.JWT;

import br.com.Mercatto.dao.UserDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private br.com.Mercatto.model.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}",username);
        userDetail = userDao.findByEmailId(username);
        if(!Objects.isNull(username)){
            return new User(userDetail.getEmail(),userDetail.getSenha(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found.");
        }
    }
    public br.com.Mercatto.model.User getUserDetail(){
        return userDetail;
    }

}
