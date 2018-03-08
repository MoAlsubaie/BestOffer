package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity  {
final Context context = this ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void vrify(View v){
try{
    EditText editText = (EditText) findViewById(R.id.fristname);
    String fn = editText.getText().toString();

    editText = (EditText) findViewById(R.id.lastname);
    String ln = editText.getText().toString();

     editText = (EditText) findViewById(R.id.Email);
    String Em = editText.getText().toString();

    editText = (EditText) findViewById(R.id.Password);
    String ps = editText.getText().toString();

    editText = (EditText) findViewById(R.id.vrify);
    String vp = editText.getText().toString();

    if(isEmpty(fn) || isEmpty(ln) || isEmpty(Em) || isEmpty(ps) || isEmpty(vp))
        throw new NullPointerException() ;
    if(!vp.equals(ps))
        throw new InputMismatchException() ;
    if(!isemailValid(Em))
        throw new EmailExeption() ;

    Toast.makeText(this, "Signing up...", Toast.LENGTH_SHORT).show();
    new signupConn(this).execute(fn , ln , Em , ps) ;

    }catch (InputMismatchException e){

    showAlert(getResources().getString(R.string.massege1));
}catch (NullPointerException e){
    showAlert(getResources().getString(R.string.fillData));
} catch (EmailExeption e){
    showAlert(getResources().getString(R.string.emailEx));
}


    }

    private void showAlert(String str_mesg){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning").setMessage(str_mesg);
        builder.setPositiveButton("Okay",null);
        builder.create().show();
    }

    public boolean isemailValid(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isEmpty(String etText) {
        return etText.trim().length() == 0;
    }






}

