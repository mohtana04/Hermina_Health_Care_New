package com.example.herminahealtcenter.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.herminahealtcenter.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class SessionsManager {
    // User name (make variable public to access from outside)
    public static final String KEY_NOMR = "nomr";
    // Email address (make variable public to access from outside)
    public static final String KEY_NAMA = "nmpasien";
    public static final String KEY_GENDER = "gender";
    // Sharedpref file name
    private static final String PREF_NAME = "PandawaCodePref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String IS_REGISTER = "IsRegisterIn";
    // Shared Preferences
    SharedPreferences pref;
    NavigationView navigationView;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionsManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create Login session
     */
    public void createLoginSession(String nomr, String nmpasien, String gender) {
        // Storing Login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing nomr in pref
        editor.putString(KEY_NOMR, nomr);

        // Storing name in pref
        editor.putString(KEY_NAMA, nmpasien);

        // Storing gender in pref
        editor.putString(KEY_GENDER, gender);

        // commit changes
        editor.commit();
    }

    public void createRegistrasiSession(String email){
        editor.putBoolean(IS_REGISTER, true);

        editor.putString(KEY_NAMA, email);
        // commit changes
        editor.commit();
    }

    /**
     * Check Login method wil check user Login status
     * If false it will redirect user to Login page
     * Else won't do anything
     */

    public void checkRegister(){
        if (!this.isRegister()) {

        }
    }

    public void checkLogin() {
        // Check Login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
//
//            navigationView = (NavigationView) navigationView.findViewById(R.id.nav_view);
//            Menu nav_Menu = navigationView.getMenu();
//            nav_Menu.findItem(R.id.nav_logout).setVisible(false);

        }

    }


    /**
     * Get stored session data
     */
    public String getUserName() {
        // return user
        return pref.getString(KEY_NAMA, null);
    }

    public String getUserNomr() {
        // return user
        return pref.getString(KEY_NOMR, null);
    }

    public String getUserGender() {
        // return user
        return pref.getString(KEY_GENDER, null);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public void changeEmail(){
        editor.clear();
        editor.commit();

//        Intent i = new Intent(_context, RegistrasiActivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        _context.startActivity(i);
    }

    /**
     * Quick check for Login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isRegister() { return pref.getBoolean(IS_REGISTER, false);}
}
