package sg.edu.np.s10189112.week12;

import java.security.PublicKey;

public class UserData {

    private String MyUserName;
    private String MyPassword;

    public UserData(){

    }

    public UserData(String myUserName,String muPassword)
    {
        MyUserName = myUserName;
        MyPassword = muPassword;
    }

    public String getMyUserName() {
        return MyUserName;
    }

    public void setMyUserName(String myUserName) {
        MyUserName = myUserName;
    }

    public String getMyPassword() {
        return MyPassword;
    }

    public void setMyPassword(String myPassword) {
        MyPassword = myPassword;
    }


}

