package dtos;

import entities.UserCrypto;

import java.util.List;

public class UserCryptoList {

    private List<UserCryptoDTO> userCryptoDTOList;

    public UserCryptoList(List<UserCryptoDTO> userCryptoDTOList) {
        this.userCryptoDTOList = userCryptoDTOList;
    }

    public List<UserCryptoDTO> getUserCryptoDTOList() {
        return userCryptoDTOList;
    }

    public void setUserCryptoDTOList(List<UserCryptoDTO> userCryptoDTOList) {
        this.userCryptoDTOList = userCryptoDTOList;
    }
}
