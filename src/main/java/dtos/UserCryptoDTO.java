package dtos;

public class UserCryptoDTO {
        private UserDTO userDTO;
        private CryptoValutaDTO cryptoValutaDTO;
        private int count;

    public UserCryptoDTO(UserDTO userDTO, CryptoValutaDTO cryptoValutaDTO, int count) {
        this.userDTO = userDTO;
        this.cryptoValutaDTO = cryptoValutaDTO;
        this.count = count;
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
