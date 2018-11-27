In this project we implement a Unity game in Android Studio using libraries. Also, we include the moving enemy in the vertical axes, so not exhibit the problem that showed previously. All the paths mentioned are relatives to the project, so don’t forget to put PROJECT_NAME/.. before the paths.
The steps that we follow are this:

EXPORT FROM UNITY

We create a script where we let the user go back in the android application. Then export the project into an Android project as follows:



Very important to select Auto Graphics API.

ANDROID STUDIO

Once we have exported the game, it’s time to open with the Android Studio. Import project from Grade --> Yes (to sync automatically gradle files).
1. Comment the Androidmanifest.xml file, in particular <intent-filter>, to make our project non executable.
<!--<intent-filter>
  <action android:name="android.intent.action.MAIN" />
  <category android:name="android.intent.category.LAUNCHER" />
  <category android:name="android.intent.category.LEANBACK_LAUNCHER" />
</intent-filter>-->

2. Insert in the module gradle file, the next line, changing application for library:
apply plugin: 'com.android.library'

3. If we have some errors building or updating the gradle, this can help you: Add this in buildscript and allprojects of module gradle file.

repositories {
    google()
    jcenter()
}


4. Finally, debug the project and find the .aar file generated: /build/outputs/aar/


NATIVE ANDROID APP USING JAR/AAR LIBRARY


Create a new project, and import new module JAR/AAR Package, select the .aar file created earlier, and finish the import. Make sure that we have 

include ':app', ':your_aar_file_name', and add the following to module app gradle.build
compile project(":your_aar_file_name")
compile fileTree(dir: 'libs', include: ['*.jar']). Now sync gradle.

If we have a problem like this: MANIFEST MERGER FAILED WITH MULTIPLE ERROR, SEE LOGS. Add the following lines to our Androidmanifest.xml file.
* adding to manifest tag xmlns:tools="http://schemas.android.com/tools"
* adding to the application tagtools:replace="android:icon,android:theme"



CREATE A SEONCDARY LAYOUT THAT CALLS THE MAIN LAYOUT AND RUN THE GAME
We have implemented a button with the following code in MainActivity: 
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button unityButton = findViewById(R.id.button1);
        unityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ConfigurationActivity.class));
            }
        });
    }
}


Adding this to a new class called ConfigurationActivity:

public class ConfigurationActivity extends AppCompatActivity {
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(mContext, UnityPlayerActivity.class);
        startActivity(intent);
    }
}

Adding this code to the Androidmanifest.xml file:

<activity android:name=".ConfigurationActivity"
    android:parentActivityName=".MainActivity" >
    <!-- The meta-data tag is required if you support API level 15 and lower -->
    <meta-data
        android:name="android.support.PARENT_ACTIVITY"
        android:value=".MainActivity" />
</activity>

EXPORT PROJECT AS AN APK
To generate apk file go to: RUN --> EDIT CONFIGURATIONS --> SELECT ‘+’ AND CREATE GRADLE --> SELECT OUR GRADLE PORJECT --> ASSIGN ‘ASSEMBLE’ IN TASKS --> APPLY. 
The apk is generated in the following path: /app/build/outputs/apk/



