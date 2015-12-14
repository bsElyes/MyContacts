package interact.io.mycontacts.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import interact.io.mycontacts.R;

public class MakeCall extends AppCompatActivity {

    ImageView b1;
    String number="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_call);
        b1=(ImageView)findViewById(R.id.call);
        EditText numberText = (EditText)findViewById(R.id.numberToCall);
        number = numberText.getText().toString();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(number);

            }
        });
    }

    private void call(String number) {
        Intent in=new Intent(Intent.ACTION_CALL, Uri.parse(number));
        try{
            startActivity(in);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
        }
    }
}