//Stock
package org.firstinspires.ftc.teamcode.subsystems.old;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame.LABELS;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class old_CameraSubsystem extends SubsystemBase {

    //private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/sleeveMapV1/model.tflite";

    private static final String[] LABELS = {
            "0 Hexagon",
            "1 Ring",
            "2 Maze"
    };

    private static final String VUFORIA_KEY = "Acb2ipn/////AAABmdczhD2DV0Bhiuv5r1dmGCKLw3H3ye5rlwjJwIX6ed/fSjotHokCR8sQ18eoI0pAxFliQ6KUtAw48B8aNlRv8D8lKqBkWgnJZB4a4Ss0xaR6yZWRp6STcmfbSe+s7vtVwkVkSEmE+lPo8JlsnocaZMc9iHrZuyrBD2KCtiOCJyXHVzIDUDhGgxQ60qYnLIQFGERHgsQ9ut4B14TFpFzFIcDhk3z+yiUsh3pHEA5IZ6vWMx+VF/5/Bq42NlPKdvEd57h4wmeeCezEQR6XzwRMWjDY685ZsStR0DxHqeB7dZ0sjrsWDRWhr1A1WTdQmAcTmN/uTbyrjkW8RxY7osTfJYaw1atEw4OlCa6hcpw7ufWG";

    private VuforiaLocalizer vuforia; //stores instance of vuforia engine
    private TFObjectDetector tfod; //stores instance of object detection field
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    List<Recognition> updatedRecognitions;

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() { //stock, should be good
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() { //modified
        int tfodMonitorViewId = this.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", this.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        //tfObjectDetector.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
        if (tfod == null)  Log.e("ROBOT", "initTfod: tfObjectDetector is null");
        else Log.d("ROBOT", "initTfod: tfObjectDetector is NOT null");
    }


    public old_CameraSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap=hardwareMap;
        this.telemetry=telemetry;
        initVuforia();
        initTfod();
    }

    public void DetectObjects() {//should be good?
        List<Recognition> newUpdatedRecognitions=null;
        if (tfod != null)
            newUpdatedRecognitions = tfod.getUpdatedRecognitions();

        if (updatedRecognitions != null) {
            if(newUpdatedRecognitions!= null) updatedRecognitions=newUpdatedRecognitions;
            this.telemetry.addData("# Objects Detected", updatedRecognitions.size());
            for (Recognition recognition : updatedRecognitions) {
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );
            }
        }
        else{
            telemetry.addLine("camera is called but no changes");
        }
    }

    public void periodic(){

        DetectObjects();
    }

//            public void
//                if (tfod != null) {
//                    // getUpdatedRecognitions() will return null if no new information is available since
//                    // the last time that call was made.
//
//
//
//                        // step through the list of recognitions and display image position/size information for each one
//                        // Note: "Image number" refers to the randomized image orientation/number
//                        for (Recognition recognition : updatedRecognitions) {
//                            double col = (recognition.getLeft() + recognition.getRight()) / 2 ;
//                            double row = (recognition.getTop()  + recognition.getBottom()) / 2 ;
//                            double width  = Math.abs(recognition.getRight() - recognition.getLeft()) ;
//                            double height = Math.abs(recognition.getTop()  - recognition.getBottom()) ;
//
//
//                            telemetry.addData(""," ");
//                            telemetry.addData("- Position (Row/Col)","%.0f / %.0f", row, col);
//                            telemetry.addData("- Size (Width/Height)","%.0f / %.0f", width, height);
//                        }
//                        telemetry.update();
//                    }
}