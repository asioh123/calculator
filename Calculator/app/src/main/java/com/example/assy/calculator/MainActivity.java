package com.example.assy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView VI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VI=(TextView)findViewById(R.id.Txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void numClick(View sender)//function to add numbers and signs
    {
        Button bt = (Button)sender;

        char SignPress = bt.getText().toString().charAt(0);



        if(SignPress=='3')//chack if the button the user press is 3
        {
            Log.i("number 3 click","number 3 click");
        }

        VI.append(bt.getText());
    }

    public void ClearClick(View sender)//function to clear the label
    {
        VI.setText("");
    }


    public void ResClick(View sender)//function to take the numbers and calculate
    {
       String result = VI.getText().toString(); //take the text from the label convert to string
        String[] ary = result.split(""); // take the string and convert to arrayString

        int res=0,i=1;
        String temp="",number="";


        while(i<ary.length)
        {
            temp=ary[i];//temp get the next char


            if(temp.charAt(0)=='-' && i==1)//if its minus number first
            {

                i++;
                if(i==ary.length)//if only sign
                {
                    VI.setText("");
                    return;
                }
                temp=ary[i];

                if(temp.charAt(0)=='-' || temp.charAt(0)=='+')//chack if the insert is ok
                {
                    VI.setText("");
                    return;
                }

                while(temp.charAt(0)!='+' && temp.charAt(0)!='-')//chack when the next sign come up
                {
                    number += temp;
                    i++;

                    if(i==ary.length)
                        break;

                    temp=ary[i];
                }

                res-=Integer.parseInt(number);
                number="";


            }
            if(temp.charAt(0)=='+' && i==1)//if its plus number first
            {

                i++;
                if(i==ary.length)//if only sign
                {
                    VI.setText("");
                    return;
                }
                temp=ary[i];

                if(temp.charAt(0)=='-' || temp.charAt(0)=='+')//chack if the insert is ok
                {
                    VI.setText("");
                    return;
                }

                while(temp.charAt(0)!='+' && temp.charAt(0)!='-')//chack when the next sign come up
                {
                    number += temp;
                    i++;

                    if(i==ary.length)
                        break;

                    temp=ary[i];
                }

                res+=Integer.parseInt(number);
                number="";


            }
            else if(temp.charAt(0)=='+')
            {
                if(res==0)
                {
                    res += Integer.parseInt(number);
                    number="";
                }
                
                i++;
                if(i==ary.length)//chack if the sign is last
                {
                    VI.setText("");
                    return;
                }
                temp=ary[i];

                if(temp.charAt(0)=='-' || temp.charAt(0)=='+')//chack if the insert is ok
                {
                    VI.setText("");
                    return;
                }

                while(temp.charAt(0)!='+' && temp.charAt(0)!='-')//chack when the next sign come up
                {
                    number += temp;
                    i++;

                    if(i==ary.length)
                        break;

                    temp=ary[i];
                }

                res+=Integer.parseInt(number);
                number="";

            }
            else if(temp.charAt(0)=='-')
            {

                if(res==0)
                {
                    res += Integer.parseInt(number);
                    number="";
                }

                i++;
                if(i==ary.length)//chack if the sign is last
                {
                    VI.setText("");
                    return;
                }
                temp=ary[i];

                if(temp.charAt(0)=='-' || temp.charAt(0)=='+')//chack if the insert is ok
                {
                    VI.setText("");
                    return;
                }

                while(temp.charAt(0)!='+' && temp.charAt(0)!='-')//chack when the next sign come up
                {
                    number += temp;
                    i++;

                    if(i==ary.length)
                        break;

                    temp=ary[i];
                }

                res-=Integer.parseInt(number);
                number="";
            }
            else
            {
                number += temp;
                i++;
            }
        }

        if(res==20)//chack if the result of the numbers are 20
        {
            Log.i("sum numbers 20","sum numbers 20");
        }

        number=String.valueOf(res);//convert int to string



        VI.setText(number);

        }

    }








