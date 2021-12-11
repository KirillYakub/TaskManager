package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller extends Thread {

    private Stage stage = new Stage();
    private Scheduler scheduler = new Scheduler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea actualProcessField = new TextArea();

    @FXML
    private Button addTactBtn;

    @FXML
    private TextArea busyBlocksField = new TextArea();

    @FXML
    private TextArea busyCoresField = new TextArea();

    @FXML
    private Button exitBtn;

    @FXML
    private TextArea finishedProcessField = new TextArea();

    @FXML
    private TextArea rejectedProcessField = new TextArea();

    @FXML
    private Button startProgramBtn;

    @Override
    public void run(){
        while(Configuration.isProgramWork()) {
            actualProcessField.setText(Configuration.getActualProcesses());
            rejectedProcessField.setText(Configuration.getRejectedProcesses());
            finishedProcessField.setText(Configuration.getFinalProcesses());
            busyBlocksField.setText(Configuration.getMemoryBlocks());
            busyCoresField.setText(Configuration.getActiveCores());
            try {
                Thread.sleep(Configuration.threadSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        startProgramBtn.setOnAction(event -> {
            if(!Configuration.isProgramWork()) {
                Scheduler.programStart();
                scheduler.start();
                start();
            }
        });
        addTactBtn.setOnAction(event -> {
            if(Configuration.isProgramWork())
                ProgramTimer.incTact();
        });
        exitBtn.setOnAction(event -> {
            if(Configuration.isProgramWork())
                Configuration.setProgramWork(false);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            stage.close();
            System.exit(0);
        });
    }
}