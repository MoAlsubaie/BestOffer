package bestoffer.kau.edu.bestoffer.Test;


import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.DialogInterface;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bestoffer.kau.edu.bestoffer.MainActivity;
import bestoffer.kau.edu.bestoffer.R;
import bestoffer.kau.edu.bestoffer.browseActivity;

public class SigninTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity signin;
    private EditText email ;
    private EditText password ;
    private Button button ;
    private Instrumentation ins  = getInstrumentation();

    public SigninTest() {
        super(MainActivity.class);

    }
    protected  void setUp()throws Exception{
        setActivityInitialTouchMode(true);
        ins = getInstrumentation() ;
        signin= getActivity();
        email = (EditText) signin.findViewById(R.id.Email);
        password=(EditText)signin.findViewById(R.id.Password);
        button=(Button) signin.findViewById(R.id.SignIn);



    }
    @MediumTest
    public void testNotNull(){
        assertNotNull("Activity is null", signin);
        assertNotNull("EmailEditText is null", email);
        assertNotNull("PasswordEditText is null", password);
        assertNotNull("Button is null", button);
    }
  @MediumTest
    public void testEmptyClick() {
      TouchUtils.clickView(this, button);
      AlertDialog dialog = signin.getLastDialog();
      if (dialog.isShowing()) {
          try {
              performClick(dialog.getButton(DialogInterface.BUTTON_POSITIVE));
          } catch (Throwable e) {
              e.printStackTrace();
          }

      }
  }


    @LargeTest
    public void testsignIn ()  {
        Instrumentation.ActivityMonitor browseMon = ins.addMonitor(browseActivity.class.getName() , null , false) ;
        getInstrumentation().waitForIdleSync();

        final View decorView = signin.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,button);
        signin.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                email.requestFocus();
                email.setText("x@x.com");
                password.requestFocus();
                password.setText("f");
            }
        });
        try {
            performClick(button);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        browseActivity browse = (browseActivity) browseMon.waitForActivityWithTimeout(25000) ;

        assertNotNull("browse is null",browse);

    }



    private void performClick(final Button button) throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
    }

}






