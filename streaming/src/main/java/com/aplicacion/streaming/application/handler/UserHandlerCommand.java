package com.aplicacion.streaming.application.handler;

import com.aplicacion.streaming.application.dto.LoginDTO;
import com.aplicacion.streaming.application.dto.UserResponseDTO;
import com.aplicacion.streaming.application.dto.command.UserDTOCommand;
import com.aplicacion.streaming.application.factory.UserFactory;
import com.aplicacion.streaming.domain.model.User;
import com.aplicacion.streaming.domain.port.in.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@Transactional
public class UserHandlerCommand {

    private final IUserServices iUserServices;

    private static final String ALGORITHM = "AES";
    private static final String KEY = "UK8Z09KfAlAhm3DmeHrCD8TVhZiiogZkPCYfXGnI3qc=";
    private static final byte[] decodedKey = Base64.getDecoder().decode(KEY);
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserHandlerCommand(IUserServices iUserServices) {
        this.iUserServices = iUserServices;
    }

    public void handle(UserDTOCommand userDTOCommand){
        this.iUserServices.createUser(UserFactory.convertObject(userDTOCommand,this.passwordEncoder));

    }

    public UserResponseDTO handle(LoginDTO userDTOCommand){
        String encryptedPassword = userDTOCommand.getPassword();
        String decryptedPassword= "";
        try {
             decryptedPassword = decrypt(encryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = this.iUserServices.verifyUser(userDTOCommand.getCorreo());
        if(user!=null){
            String encodedPassword = user.getPassword();
            Long userId = user.getId();
            return UserResponseDTO.builder().id(userId).isAuthent(passwordEncoder.matches(decryptedPassword, encodedPassword)).build();
        }
        return UserResponseDTO.builder().id(0L).isAuthent(false).build();




    }

    public  String decrypt(String encryptedValue) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(decodedKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decrypted = cipher.doFinal(decodedValue);
        return new String(decrypted);
    }
}
