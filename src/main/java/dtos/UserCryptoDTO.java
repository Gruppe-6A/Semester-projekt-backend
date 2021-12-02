package dtos;

import entities.CryptoValuta;
import entities.UserCrypto;

import java.util.ArrayList;
import java.util.List;

public class UserCryptoDTO {
        private UserDTO userDTO;
        private CryptoValutaDTO cryptoValutaDTO;
        private int count;

    public UserCryptoDTO(UserDTO userDTO, CryptoValutaDTO cryptoValutaDTO, int count) {
        this.userDTO = userDTO;
        this.cryptoValutaDTO = cryptoValutaDTO;
        this.count = count;
    }

    public UserCryptoDTO(UserCrypto uc) {
        this.userDTO = new UserDTO(uc.getUser());
        this.cryptoValutaDTO = new CryptoValutaDTO(uc.getCryptoValuta());
        this.count = uc.getCount();
    }

    public static List<UserCryptoDTO> getUserCryptoDTO(List<UserCrypto> uc){
        List<UserCryptoDTO> ucDTO = new ArrayList();
        uc.forEach(uces->ucDTO.add(new UserCryptoDTO(uces)));
        return ucDTO;
    }


    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public CryptoValutaDTO getCryptoValutaDTO() {
        return cryptoValutaDTO;
    }

    public void setCryptoValutaDTO(CryptoValutaDTO cryptoValutaDTO) {
        this.cryptoValutaDTO = cryptoValutaDTO;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
