package bestoffer.kau.edu.bestoffer;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startSignup(View v) {

        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);

    }

    public void startbrowse(View v) {
        try {

            EditText editText = (EditText) findViewById(R.id.Email);
            String Em = editText.getText().toString();

            editText = (EditText) findViewById(R.id.Password);
            String ps = editText.getText().toString();
            if (isEmpty(Em) || isEmpty(ps))
                throw new NullPointerException();
            if (!isemailValid(Em))
                throw new EmailExeption();


            Toast.makeText(this, "Signing in...", Toast.LENGTH_SHORT).show();
            new signinConn(this).execute(Em, ps);

        } catch (NullPointerException e) {
            showAlert(getResources().getString(R.string.fillData));
        } catch (EmailExeption e) {
            showAlert(getResources().getString(R.string.emailEx));
        }
    }

    private void showAlert(String str_mesg) {

        builder = new AlertDialog.Builder(this) ;
        builder.setTitle("Warning").setMessage(str_mesg);
        builder.setPositiveButton("Okay", null);
        builder.create().show();
    }

    public boolean isemailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isEmpty(String etText) {
        return etText.trim().length() == 0;
    }



}