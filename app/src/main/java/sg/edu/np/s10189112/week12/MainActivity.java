package sg.edu.np.s10189112.week12;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private TextView tv;
    SharedPreferences sharedpref;
    public static final String MY_GLOBAL_PREFS = "MyPrefs";
    public static final String MY_USERNAME = "MyUser";
    public static final String MY_PASSWORD = "MyPass";
    private static final String Tag = "MainActivity.java";

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.txtnew);
        tv.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event) {

        Log.v(Tag, "Touch start");
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }

    public void onClick(View v)
    {
        final EditText etuser = (EditText) findViewById(R.id.txtname);
        final EditText etpass = (EditText) findViewById(R.id.txtpass);

        //if (isValidUser(etuser.getText().toString()) && isValidPass(etpass.getText().toString())) {
        if (isValidUser(etuser.getText().toString(),etpass.getText().toString())){
            Intent intent = new Intent(MainActivity.this,Main3Activity.class);
            Toast.makeText(MainActivity.this,"valid user",Toast.LENGTH_LONG).show();
            startActivity(intent);

        }
        else
        {
            Toast.makeText(MainActivity.this,"Invalid user",Toast.LENGTH_LONG).show();
        }


    }

    public boolean isValidUser(String userName, String password)
    {
        UserData dbData = dbHandler.findUser(userName);
        //UserData dbData1 = dbHandler.findUser(password);


        if ((dbData.getMyUserName().equals(userName)) && dbData.getMyPassword().equals(password))
        {
            return true;
        }
        return false;
    }



}
