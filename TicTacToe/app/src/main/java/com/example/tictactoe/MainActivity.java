package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btnRstGme, btnNewGme;
    TextView headerText, tvO, tvX, tvScoreO, tvScoreX;
    int Player_O = 0;
    int Player_X = 1;

    int activePlayer = Player_O;

    int[] filledPosition = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean isGameActive = true;

    int OCounter = 0;
    int XCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn0 = findViewById(R.id.buttonB0);
        btn1 = findViewById(R.id.buttonB1);
        btn2 = findViewById(R.id.buttonB2);
        btn3 = findViewById(R.id.buttonB3);
        btn4 = findViewById(R.id.buttonB4);
        btn5 = findViewById(R.id.buttonB5);
        btn6 = findViewById(R.id.buttonB6);
        btn7 = findViewById(R.id.buttonB7);
        btn8 = findViewById(R.id.buttonB8);
        btn8 = findViewById(R.id.buttonB8);
        btnRstGme = findViewById(R.id.buttonRstGme);
        btnNewGme = findViewById(R.id.buttonNewGame);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        headerText = findViewById(R.id.header_text);
        tvO = findViewById(R.id.tvO);
        tvX = findViewById(R.id.tvX);
        tvScoreX = findViewById(R.id.XScore);
        tvScoreO = findViewById(R.id.OScore);

        headerText.setText("Player O Turn");
        headerText.setTextColor(Color.parseColor("#448EE2"));

        btnRstGme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContinueGame();

            }
        });

        btnNewGme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newGameDialog("Are you sure?");
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(!isGameActive) {
            return;
        }
        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        if(filledPosition[clickedTag] != -1)
        {
            return;
        }

        filledPosition[clickedTag] = activePlayer;

        if(activePlayer == Player_O)
        {
            clickedBtn.setText("O");
            clickedBtn.setTextColor(getResources().getColor(R.color.teal_700));
            //clickedBtn.setBackgroundColor(getResources().getColor(R.color.teal_700));
            activePlayer = Player_X;
            headerText.setText("Player X Turn");
            headerText.setTextColor(Color.parseColor("#FF0000"));

        }
        else
        {
            clickedBtn.setText("X");
            clickedBtn.setTextColor(getResources().getColor(R.color.teal_200));
            activePlayer = Player_O;
            headerText.setText("Player O Turn");
            headerText.setTextColor(Color.parseColor("#448EE2"));
        }


        checkWinner();


    }

    private void checkWinner()
    {
        int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i = 0; i<8; i++)
        {
            int val0 = winningPositions[i][0];
            int val1 = winningPositions[i][1];
            int val2 = winningPositions[i][2];

            if(filledPosition[val0] == filledPosition[val1] && filledPosition[val1] == filledPosition[val2])
            {
                if(filledPosition[val0] != -1)
                {
                    isGameActive = false;

                  if(filledPosition[val0] == Player_O)
                  {
                      OCounter++;
                      tvScoreO.setText(Integer.toString(OCounter));
                      tvO.setVisibility(tvO.VISIBLE);
                      showDialog("Player O is winner.");
                  }
                  else
                  {
                      XCounter++;
                      tvScoreX.setText(Integer.toString(XCounter));
                      tvX.setVisibility(tvX.VISIBLE);
                      showDialog("Player X is winner.");

                  }
                }

            }

        }
    }


    private  void  showDialog(String winnerText)
    {
        new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ContinueGame();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NewGame();
            }
        }).show();
    }

    private  void  newGameDialog(String newGameText)
    {
        new AlertDialog.Builder(this).setTitle(newGameText).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NewGame();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    private  void  NewGame()
    {
        XCounter = 0;
        OCounter = 0;
        tvScoreO.setText(Integer.toString(OCounter));
        tvScoreX.setText(Integer.toString(XCounter));
      tvO.setVisibility(tvO.INVISIBLE);
      tvX.setVisibility(tvX.INVISIBLE);
      headerText.setText("Player O Turn");
      headerText.setTextColor(Color.parseColor("#448EE2"));
      activePlayer = Player_O;
      filledPosition = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1};

      btn0.setText("");
      btn1.setText("");
      btn2.setText("");
      btn3.setText("");
      btn4.setText("");
      btn5.setText("");
      btn6.setText("");
      btn7.setText("");
      btn8.setText("");


      isGameActive = true;
    }

    private  void ContinueGame(){
        tvO.setVisibility(tvO.INVISIBLE);
        tvX.setVisibility(tvX.INVISIBLE);
        headerText.setText("Player O Turn");
        headerText.setTextColor(Color.parseColor("#448EE2"));
        activePlayer = Player_O;
        filledPosition = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1};

        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        isGameActive = true;
    }
}