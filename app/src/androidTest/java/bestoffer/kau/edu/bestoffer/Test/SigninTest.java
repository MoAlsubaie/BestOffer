package bestoffer.kau.edu.bestoffer.Test;


import bestoffer.kau.edu.bestoffer.browseActivity ;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bestoffer.kau.edu.bestoffer.MainActivity;
import bestoffer.kau.edu.bestoffer.R;

public class SigninTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity signin;
    private EditText email ;
    private EditText password ;
    private Button button ;

    public SigninTest() {
        super(MainActivity.class);

    }
    protected  void setUp()throws Exception{
        setActivityInitialTouchMode(true);

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
 /* @MediumTest
    public void testEmptyClick(){
        TouchUtils.clickView(this,button);

    }*/

    @UiThreadTest
    public void testsignIn (){
        final View decorView = signin.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,button);
        email.requestFocus();
        email.setText("x@x.com");
        password.requestFocus();
        password.setText("f");
        TouchUtils.clickView(this,button);
        Instrumentation.ActivityMonitor browseMon =
                getInstrumentation().addMonitor(browseActivity.class.getName() , null , false) ;

        getInstrumentation().waitForIdleSync();

        browseActivity browse = (browseActivity) browseMon.waitForActivityWithTimeout(5000) ;

        assertNotNull("browse is null",browse);

    }

}
