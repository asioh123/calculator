package com.example.assy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

            if(temp.charAt(0)=='+')
            {
                if(res==0)
                {
                    res += Integer.parseInt(number);
                    number="";
                }
                i++;
                temp=ary[i];

                while(temp.charAt(0)!='+' && temp.charAt(0)!='-')
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
                temp=ary[i];

                while(temp.charAt(0)!='+' && temp.charAt(0)!='-')
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

        number=String.valueOf(res);

        VI.setText(number);

        }

    }








