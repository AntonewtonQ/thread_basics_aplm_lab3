package ao.co.isptec.aplm.thread_basics;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CounterThread";
    private Thread counterThread;
    private volatile boolean isRunning; // Controle para a execução da thread
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = findViewById(R.id.button_start);
        Button buttonStop = findViewById(R.id.button_stop);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCounter();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCounter();
            }
        });
    }

    private void startCounter(){
        counter = 0;
        isRunning = true;

        // Verifica se já existe uma thread em execução, para não iniciar outra
        if(counterThread != null && counterThread.isAlive()){
            return;
        }

        // Cria e inicia a thread de contagem
        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    //imprime o valor atual do contador no logcat
                    Log.d(TAG, "TIQUE-TAQUE: "+ counter);

                    //Incrementa o contador
                    counter++;

                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        Log.d(TAG, "Interruped Thead");
                    }
                }
            }
        });
    }

    private void stopCounter(){

    }
}