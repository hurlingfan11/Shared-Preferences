package com.clancy.conor.shared_preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAINACTIVITY ";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mName;
    private EditText mPassword;
    private Button mButton;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = (EditText) findViewById(R.id.etName);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mButton = (Button) findViewById(R.id.etButtonLogin);
        mCheckBox =(CheckBox) findViewById(R.id.etCheckBox);

        // create the shared preference object
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // or can use this
        //mPreferences = getSharedPreferences( "App", MODE_PRIVATE);
        mEditor = mPreferences.edit();

        // Need to call this method after you declare the shared preferences object or application
        // will crash
        checkSharedPreferences();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if checkbox is checked want to save the name and password
                // if not we don't want to save it

                if(mCheckBox.isChecked())
                {

                // set checkbox when application starts
                mEditor.putString(getString(R.string.checkbox), "True");
                mEditor.commit();

                //save the name
                String name = mName.getText().toString();
                mEditor.putString(getString(R.string.name), name);
                mEditor.commit();

                // save the password
                String password = mPassword.getText().toString();
                mEditor.putString(getString(R.string.password), password);
                    mEditor.commit();

                }else{
                    // set checkbox to false
                    mEditor.putString(getString(R.string.checkbox), "False");
                    mEditor.commit();

                    //empty string for name
                    mEditor.putString(getString(R.string.name), "");
                    mEditor.commit();

                    // empty string for password
                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();


                }
        }
        });

        mEditor.putString("key1", "mitch");
        mEditor.commit();

        // if no key/value pair exists for key key1 then the default value is default
        //mPreferences.getString("key1", "");

        String name = mPreferences.getString("key1", "default");
        String name1 = mPreferences.getString("key2", "default");
        // prints out "onCreate: name is mitch" in Logcat
        Log.d(TAG, "onCreate: name is " +name);
        // prints out "onCreate: name is default" in Logcat
        Log.d(TAG, "onCreate: name is " +name1);

    }

    /**
     * Check the shared preferences and set them accordingly
     */

    private void checkSharedPreferences(){
        // need to check for all our key value pairs

        // Store all of your keys in Strings

        // getString for checkbox. Default name is FALSE so this is what you print out if user
        // hasn't saved anything previously.
        String checkbox = mPreferences.getString(getString(R.string.checkbox), "False");
        String name = mPreferences.getString(getString(R.string.name), "");
        String password = mPreferences.getString(getString(R.string.password), "");

        mName.setText(name);
        mPassword.setText((password));

        if(checkbox.equals("True")){
            mCheckBox.setChecked(true);
             }else{
            mCheckBox.setChecked(false);
        }
    }
}
