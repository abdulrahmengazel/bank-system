import java.io.IOException;

interface AccountCreation {
    void createAccFun() throws IOException;

    String[] getUserInfoFromUser() throws IOException;

    int accNoCreation() throws IOException;

    void credWrite(int accNo, String[] accLineInfo) throws IOException;

    void balWrite(int accNo) throws IOException;

    void userWrite(int accNo, String[] accLineInfo) throws IOException;
}