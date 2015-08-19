package com.example.assy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView VI;
    char SignBefore;
    int Press=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VI = (TextView) findViewById(R.id.Txt);
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
        Button bt = (Button) sender;

        char SignPress = bt.getText().toString().charAt(0);

            if(Press==0)
            {
                if(SignPress!='+' && SignPress!='*' && SignPress!='/' && SignPress!='.')
                {
                    VI.append(bt.getText());
                    SignBefore=SignPress;
                    Press=1;
                }
            }
            else if(SignPress=='+' || SignPress=='-' || SignPress=='*' || SignPress=='/' || SignPress=='.')
            {
                if(SignBefore=='+' || SignBefore=='-' || SignBefore=='*' || SignBefore=='/' || SignBefore=='.')
                {
                    return;
                }
                else
                {
                    VI.append(bt.getText());
                    SignBefore=SignPress;
                }

            }
            else
            {
                VI.append(bt.getText());
                SignBefore=SignPress;
            }




        if (SignPress == '3')//chack if the button the user press is 3
        {
            Log.i("number 3 click", "number 3 click");
        }
    }

    public void ClearClick(View sender)//function to clear the label
    {
        VI.setText("");
        Press=0;
    }


    public void ResClick(View sender)//function to take the numbers and calculate
    {
        String result = VI.getText().toString(); //take the text from the label convert to string
        String[] ary = result.split(""); // take the string and convert to arrayString
        String FinalRes;// String get the final result from functions

        if(result.length()==0)//if the user dont add any number
        {
            return;
        }
        else if(SignBefore=='+' || SignBefore=='-' || SignBefore=='*' || SignBefore=='/' || SignBefore=='.')//if the user press = when its finish with sign
        {
            return;
        }

        ary=shuntingYard(ary);//call to shantingYard function

        FinalRes=doMath(ary);//call to doMath function after use the shuntingYard

        VI.setText(FinalRes);//Text view get the result

    }


    //function shuntingYard algorithm
    //the function get stringArray of values like 2+5-6 and return StringArray  2 5 + 6 -

    public String[] shuntingYard(String [] ary)
    {
        String Temp="",TempSign="",Number="";

        Stack <String> Res = new Stack<>();
        Stack <String> Sign = new Stack<>();

        String SignList1 = "+*";
        String SignList2 = "-/";

        int Counter=1,SignNumber=-1,SignNumberStack=-1;

        while(Counter!=ary.length)
        {
            Temp=ary[Counter];    //the next char come up
            Number="";            //reset value
            SignNumber=-1;        //reset value
            SignNumberStack=-1;   //reset value

            if(Temp.charAt(0)=='-' && Counter==1)//if the first char of the stringArray is '-' sign. if its true push it to the stack Res and proceed
            {
                Res.push(Temp);
                Counter++;
            }
            else  if(Temp.charAt(0)>='0' && Temp.charAt(0)<='9' || Temp.charAt(0)=='.' )//if the char is a number from 0-10 or . go to the wile and take all the numbers until you get sign
            {
                while (Temp.charAt(0)>='0' && Temp.charAt(0)<='9' || Temp.charAt(0)=='.')
                {
                    Number+=Temp;
                    Counter++;
                    if(Counter==ary.length)
                    {
                        break;
                    }
                    Temp=ary[Counter];
                }

                Res.push(Number);
            }
            else
            {

                SignNumber= SignList1.indexOf(Temp.charAt(0));

                if(SignNumber==-1)
                {
                    SignNumber= SignList2.indexOf(Temp.charAt(0));
                }


                if(Sign.size()==0)
                {
                    Sign.push(Temp);
                    Counter++;
                    continue;
                }
                else
                {
                    TempSign=Sign.peek();
                    SignNumberStack=SignList1.indexOf(TempSign.charAt(0));

                    if(SignNumberStack==-1)
                    {
                        SignNumberStack= SignList2.indexOf(TempSign.charAt(0));
                    }
                }

                if(SignNumber>SignNumberStack)
                {
                    Sign.push(Temp);
                }
                else if(SignNumber<=SignNumberStack)
                {
                    while(SignNumber<=SignNumberStack)
                    {
                        Res.push(Sign.pop());

                        if(Sign.size()!=0)
                        {
                            TempSign=Sign.peek();
                            SignNumberStack=SignList1.indexOf(TempSign.charAt(0));

                            if(SignNumberStack==-1)
                            {
                                SignNumberStack= SignList2.indexOf(Temp.charAt(0));
                            }

                        }
                        else{
                            break;
                        }

                    }
                    Sign.push(Temp);
                }
                    Counter++;
            }

        }

        while(Sign.size()!=0)
        {
            Res.push(Sign.pop());
        }

        String[] stockArr = new String[Res.size()];// convert Res stack string to ArrayString
        stockArr = Res.toArray(stockArr);



        return stockArr;
    }


    //function doMath
    //the function get stringArray of shuntingYard algorithm and convert the StringArray to double result

    public String doMath(String arr[])
    {
        double num1,num2,res;
        Stack <String> StackMath = new Stack<>();
        int counter=0;

        String FinalResult="",temp="-";

        while (counter!=arr.length)
        {
            if (arr[counter].charAt(0) == '+') {

                num2 = Double.parseDouble(StackMath.pop());
                num1 = Double.parseDouble(StackMath.pop());
                res = num1 + num2;
                StackMath.push(String.valueOf(res));

                counter++;
            }
            else if(arr[counter].charAt(0) == '-' && counter==0)
            {
                counter++;
                temp+=arr[counter];
                StackMath.push(temp);
                counter++;
            }
            else if (arr[counter].charAt(0) == '-') {

                num2 = Double.parseDouble(StackMath.pop());
                num1 = Double.parseDouble(StackMath.pop());
                res = num1 - num2;
                StackMath.push(String.valueOf(res));

                counter++;
            }
            else if (arr[counter].charAt(0) == '*') {

                num2 = Double.parseDouble(StackMath.pop());
                num1 = Double.parseDouble(StackMath.pop());
                res = num1 * num2;
                StackMath.push(String.valueOf(res));

                counter++;
            }
            else if (arr[counter].charAt(0) == '/') {

                num2 = Double.parseDouble(StackMath.pop());
                num1 = Double.parseDouble(StackMath.pop());
                res = num1 / num2;
                StackMath.push(String.valueOf(res));

                counter++;
            }
            else {
                StackMath.push(arr[counter]);
                counter++;
            }
        }

        FinalResult= StackMath.pop();
        return  FinalResult;
    }





}








