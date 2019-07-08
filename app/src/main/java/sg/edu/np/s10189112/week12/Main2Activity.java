package sg.edu.np.s10189112.week12;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity.java";
    SharedPreferences sharedpref;
    public static final String MY_GLOBAL_PREFS = "MyPrefs";
    public static final String MY_USERNAME = "MyUser";
    public static final String MY_PASSWORD = "MyPass";

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onCreateClick(View v){

        final EditText etuser = (EditText) findViewById(R.id.et1);
        final EditText etpass = (EditText) findViewById(R.id.editText2);

        if (isValidUser(etuser.getText().toString()) && isValidPass(etpass.getText().toString())){


            /*sharedpref =getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpref.edit();
            editor.putString(MY_USERNAME,etuser.getText().toString());
            editor.putString(MY_PASSWORD,etpass.getText().toString());
            editor.commit();*/
            UserData dbData = dbHandler.findUser(etuser.getText().toString());

            if (dbData == null){
                String dbUserName = etuser.getText().toString();
                String dbPassword = etpass.getText().toString();
                UserData dbUserData = new UserData();
                dbUserData.setMyUserName(dbUserName);
                dbUserData.setMyPassword(dbPassword);
                dbHandler.addUser(dbUserData);
                Toast.makeText(Main2Activity.this,"user created",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(Main2Activity.this,"Valid user created",Toast.LENGTH_LONG).show();

            }
            finish();
        }
        else {
            Toast.makeText(Main2Activity.this,"invalid user.\n Please try again",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isValidPass(String password)
    {
        Pattern passwordPattern;
        Matcher passwordMatcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@$%]).{6,12}$";
        passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        passwordMatcher = passwordPattern.matcher(password);

        return passwordMatcher.matches();
    }

    public boolean isValidUser(String username)
    {
        Pattern userPattern;
        Matcher userMatcher;

        final String USER_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,12}$";
        userPattern = Pattern.compile(USER_PATTERN);
        userMatcher = userPattern.matcher(username);

        return userMatcher.matches();
    }
}
