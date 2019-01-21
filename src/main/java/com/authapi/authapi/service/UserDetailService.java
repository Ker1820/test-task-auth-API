package com.authapi.authapi.service;
/*This UserDetailService provides authentication
 * If user is in DB, I set token. Else it will be thrown exception. After that save user token into DB
 * Then I put token to session and when you want to see user information, token from session are comparing with token from DB
 * I make this token with secure random bytes array and username and with hash function SHA-256*/

import com.authapi.authapi.databaseEntities.User;
import com.authapi.authapi.repositories.UserRepository;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        String token = "";
        if (user != null && !username.equals("")) {
            try {
                token = makeToken(user.getUsername());
                user.setToken(token);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            userRepository.save(user);
        } else
            throw new UsernameNotFoundException("No such user");
        putTokenIntoSesstion(token);
        return user;
    }

    private static String makeToken(String username) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        String randomSalt = arrayToStrings(random.generateSeed(50));
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String result = username + randomSalt;
        byte[] bytesHash = digest.digest(result.getBytes());
        String resultHex = HexBin.encode(bytesHash);
        return resultHex;
    }

    private static String arrayToStrings(byte[] array) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }

    private static void putTokenIntoSesstion(String token) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("token", token);
    }
}
