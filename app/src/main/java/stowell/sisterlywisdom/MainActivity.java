package stowell.sisterlywisdom;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> answers;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        answers = loadAnswers();

        final RelativeLayout question_rl = (RelativeLayout)findViewById(R.id.intro_rl);
        final RelativeLayout answer_rl = (RelativeLayout)findViewById(R.id.answer_rl);
        final EditText question_et = (EditText) findViewById(R.id.question_edittext);
        Button answer_button = (Button) findViewById(R.id.get_answer_button);
        Button  back_button = (Button) findViewById(R.id.back_button);
        final TextView answer_tv = (TextView) findViewById(R.id.answer_textview);

        answer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!question_et.getText().toString().equals("")){
                    Collections.shuffle(answers);
                    answer_tv.setText("\n\n" + answers.get(0) + "\n");
                    question_rl.setVisibility(View.GONE);
                    answer_rl.setVisibility(View.VISIBLE);
                    question_et.setText("");
                } else {
                    Toast t = Toast.makeText(context, "Enter a Question!", Toast.LENGTH_LONG);
                    t.show();
                }

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_rl.setVisibility(View.VISIBLE);
                answer_rl.setVisibility(View.GONE);
            }
        });



    }

    private ArrayList<String> loadAnswers(){
        ArrayList<String> array = new ArrayList<String>();
        try {
            //create asset manager in order to access files in the asset folder
            AssetManager am = context.getAssets();
            //use and input stream to access this file if it exists
            InputStream is = am.open("answers.txt");
            Scanner scan = new Scanner(new InputStreamReader(is));
            while (scan.hasNext()) {
                String answer = scan.nextLine();
                array.add(answer);
            }
            scan.close();
            is.close();
        } catch (IOException ex) {
        }
        Collections.shuffle(array);
        return array;
    }
}
