package com.example.katuar_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private Uri Null;
    Uri URI = Null;
    TextView TXT_CW;
    TextView TXT_T1;
    TextView TXT_T2;
    EditText EDT_CW;
    EditText EDT_T1;
    EditText EDT_T2;
    Button Btn_Put;
    TextView tvAttachment;
    String Calenfar_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void Btn_putData(View v){
        String Mail = "9150939900@mail.ru";
        String Title = "Показания "+ Calenfar_Data +" по адресу: \"Ул. Егорова, д.10, кв.95\"";
        String Data="Собственник : Мельниченко Александр Николаевич \n"
                +TXT_CW.getText()+" "+EDT_CW.getText()+"\n"
                +TXT_T1.getText()+" - "+EDT_T1.getText()+"\n"
                +TXT_T2.getText()+" - "+EDT_T2.getText();
        Toast.makeText(this, "Показания "+Calenfar_Data+" отправлены", Toast.LENGTH_LONG).show();
        Int_tent(Mail, Title, Data);
        Date();
    }
    public void Btn_getFile(View view){
        openFolder();
    }
    public void init(){
        TXT_CW=findViewById(R.id.textView_counter_water);
        TXT_T1=findViewById(R.id.textView_counterT1);
        TXT_T2=findViewById(R.id.textView_counterT2);
        EDT_CW=findViewById(R.id.editText_counter_water);
        EDT_T1=findViewById(R.id.editText_counterT1);
        EDT_T2=findViewById(R.id.editText_counterT2);
        Btn_Put=findViewById(R.id.button_putData);
        tvAttachment=findViewById(R.id.textView_tvAttachment);
    }
    public void Int_tent(String mail, String title, String TXT){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+mail));
        intent.putExtra(Intent.EXTRA_SUBJECT,title);
        intent.putExtra(Intent.EXTRA_TEXT, TXT);
        if(URI!=null)
            intent.putExtra(Intent.EXTRA_STREAM, URI);
        startActivity(intent);
    }
    public void Date(){
        Calendar cal = Calendar.getInstance();
        Calenfar_Data = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        String Parse_Cal_Data[] = Calenfar_Data.split(" ");
        Calenfar_Data=""+Parse_Cal_Data[2]+" "+Parse_Cal_Data[3];
    }
    public void openFolder(){
        Intent data = new Intent(Intent.ACTION_GET_CONTENT);
        data.setType("file/*");
        startActivityForResult(data,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String FileName = data.getData().getLastPathSegment();
                    tvAttachment.setText(FileName);
                    URI = data.getData();
                }
                break;
        }
    }
}