package com.example.nasser.assignment3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView ,imageView1;
    int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnCameraClick(View v )
    {
        imageView = (ImageView) findViewById(R.id.imageView);
        Intent myintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(myintent,0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap =(Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

    public void openGallery(View v)
    {
        imageView1 = (ImageView) findViewById(R.id.imageView);
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }
    protected void onActivityResult1(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageuri = data.getData();
        imageView1.setImageURI(imageuri);
    }

    public void btnWhatsappClick (View v)
    {
        EditText txtFname = (EditText) findViewById(R.id.txtfirstname);
        EditText txtLname = (EditText) findViewById(R.id.txtlastname);
        EditText txt = (EditText) findViewById(R.id.txtphonenumber);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "hey i am "+ txtFname.getText().toString()
                    +  " "     + txtLname.getText().toString()
                    + ", i am using WhatsApp, you can text me on it" );

        sendIntent.setType("text/plain");
       // sendIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.com.WhatsApp"));
        //startActivity(Intent.createChooser(sendIntent, ""));
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);

    }

    public void btnSignupclick (View v )
    {
        String str;
        EditText txtFname = (EditText) findViewById(R.id.txtfirstname);
        EditText txtLname = (EditText) findViewById(R.id.txtlastname);
        CheckBox chmale = (CheckBox) findViewById(R.id.checkmale);
        CheckBox chfemale = (CheckBox) findViewById(R.id.checkfemale);
        EditText txtnumber = (EditText) findViewById(R.id.txtphonenumber);
        EditText txtmail = (EditText) findViewById(R.id.txtEmail);
        EditText txtmsg = (EditText) findViewById(R.id.txtmsg);

        if (chmale.isChecked())
        {
            str = "male";
        }
        else
        {
            str="female";
        }

        Intent myintent = new Intent();
        myintent.setAction(Intent.ACTION_SEND);
        myintent.putExtra(Intent.EXTRA_EMAIL, new String[] {"zamel@example.com"});

        myintent.putExtra(Intent.EXTRA_TEXT,
                " Heey my name is " +
                txtFname.getText().toString() + " " +
                txtLname.getText().toString() +"\n"+
                " and my phone number is: " + txtnumber.getText().toString() + " " +
                "u can talk to me on whatsapp, " +"\n"+
                " Email: " + txtmail.getText().toString() + "\n"+
                " for sure i am "+str.toString() + "\n" +
                        "_____________"+ "\n" +
                "this is my message to you mr zamel : "+"\n"
                + txtmsg.getText().toString());
        myintent.setType("text/plain");
        myintent.setPackage("com.google.android.gm");
        startActivity(myintent);


    }

    public void btnNumberClick (View v )
    {
        EditText txt = (EditText) findViewById(R.id.txtphonenumber);
        startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+txt.getText())));
    }

    public void btnSendClick (View v )
    {
        EditText txt = (EditText) findViewById(R.id.txtEmail);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        emailIntent.setPackage("com.google.android.gm");
        startActivity(emailIntent);
    }
}
