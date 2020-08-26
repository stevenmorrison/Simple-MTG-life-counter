package com.example.simplemtglifecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplemtglifecounter.databinding.ActivityMainBinding;

/**
 *  Main Activity for app
 */
public class MainActivity extends AppCompatActivity {
    private static int startLife = 20;
    private TextView lifeTotal1;
    private TextView lifeTotal2;
    private int player1Life = 20;
    private int player2Life = 20;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Use View binding to target UI elements
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        lifeTotal1 = binding.CurrectLifePlayer1;
        lifeTotal2 = binding.CurrentLifePlayer2;
    }
    public void chageLifeColor(int lifeleft, TextView atextView){
        if (lifeleft >= 15){
            atextView.setTextColor(Color.GREEN);
        }
        else if (lifeleft < 15 && lifeleft > 5)
            atextView.setTextColor(Color.rgb(255, 128, 0)); //Orange
        else if (lifeleft <= 5)
            atextView.setTextColor(Color.RED);
    }
    public void AddLifePlayer1(View view) {
        player1Life++;
        if(lifeTotal1 != null) {
            lifeTotal1.setText(Integer.toString(player1Life));
            chageLifeColor(player1Life, lifeTotal1);

        }
    }

    public void SubtractLifePlayer1(View view) {
        player1Life--;
        if(lifeTotal1 != null) {
            lifeTotal1.setText(Integer.toString(player1Life));
            chageLifeColor(player1Life, lifeTotal1);
        }
        if(player1Life <= 0){
            Toast toast = Toast.makeText(this, R.string.Player_Two_Wins, Toast.LENGTH_LONG);
            toast.show();
            resetGame();
        }
    }

    public void AddLifePlayer2(View view) {
        player2Life++;
        if(lifeTotal2 != null) {
            lifeTotal2.setText(Integer.toString(player2Life));
            chageLifeColor(player2Life, lifeTotal2);
        }

    }

    public void SubtractLifePlayer2(View view) {
        player2Life--;
        if(lifeTotal2 != null) {
            lifeTotal2.setText(Integer.toString(player2Life));
            chageLifeColor(player2Life, lifeTotal2);
        }
        if(player2Life <= 0){
            Toast toast = Toast.makeText(this, R.string.Player_One_Wins, Toast.LENGTH_LONG);
            toast.show();
            resetGame();

        }
    }
    public void resetGame(){
        player1Life = startLife;
        player2Life = startLife;
        lifeTotal1.setText(Integer.toString(player1Life));
        lifeTotal2.setText(Integer.toString(player2Life));

        chageLifeColor(player1Life, lifeTotal1);
        chageLifeColor(player2Life, lifeTotal2);
    }

    public void SetPlayer1Name(View view) {
    }

    public void SetPlayer2Name(View view) {
    }
}