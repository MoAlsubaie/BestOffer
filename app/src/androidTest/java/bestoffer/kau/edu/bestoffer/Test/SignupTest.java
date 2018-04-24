package bestoffer.kau.edu.bestoffer.Test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;

import bestoffer.kau.edu.bestoffer.R;
import bestoffer.kau.edu.bestoffer.Signup;

public class SignupTest extends ActivityInstrumentationTestCase2<Signup> {

    private Signup Activity ;
    private EditText fname ;
    private EditText lname ;
    private EditText email ;
    private EditText password ;
    private EditText Vpassword ;
    private Button button ;
    private Instrumentation ins  = getInstrumentation();


    public SignupTest(Class<Signup> activityClass) {
        super(activityClass);
    }

    protected  void setUp()throws Exception{
        setActivityInitialTouchMode(true);
        ins = getInstrumentation() ;
        Activity= getActivity();
        fname = (EditText) Activity.findViewById(R.id.fristname);
        lname = (EditText) Activity.findViewById(R.id.lastname) ;
        email = (EditText) Activity.findViewById(R.id.Email);
        password=(EditText)Activity.findViewById(R.id.Password);
        Vpassword=(EditText)Activity.findViewById(R.id.vrify) ;
        button=(Button) Activity.findViewById(R.id.button);



    }

    @MediumTest
    public void testNotNull(){
        assertNotNull("Activity is null", Activity);
        assertNotNull("EmailEditText is null", fname);
        assertNotNull("EmailEditText is null", lname);
        assertNotNull("EmailEditText is null", email);
        assertNotNull("PasswordEditText is null", password);
        assertNotNull("Button is null", button);
    }





}
