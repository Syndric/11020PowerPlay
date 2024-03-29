package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class CameraSubsystem extends SubsystemBase {
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/sleeveMapV1/model.tflite";

    private static final String[] LABELS = {
            "0 Hexagon",
            "1 Ring",
            "2 Maze"
    };

    private static final String VUFORIA_KEY = "Acb2ipn/////AAABmdczhD2DV0Bhiuv5r1dmGCKLw3H3ye5rlwjJwIX6ed/fSjotHokCR8sQ18eoI0pAxFliQ6KUtAw48B8aNlRv8D8lKqBkWgnJZB4a4Ss0xaR6yZWRp6STcmfbSe+s7vtVwkVkSEmE+lPo8JlsnocaZMc9iHrZuyrBD2KCtiOCJyXHVzIDUDhGgxQ60qYnLIQFGERHgsQ9ut4B14TFpFzFIcDhk3z+yiUsh3pHEA5IZ6vWMx+VF/5/Bq42NlPKdvEd57h4wmeeCezEQR6XzwRMWjDY685ZsStR0DxHqeB7dZ0sjrsWDRWhr1A1WTdQmAcTmN/uTbyrjkW8RxY7osTfJYaw1atEw4OlCa6hcpw7ufWG";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    List<Recognition> updatedRecognitions;

    public CameraSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        initVuforia();
        initTfod();
    }

    public void DetectObjects() {
        List<Recognition> newUpdatedRecognitions = null;
        if(tfod != null) {
            newUpdatedRecognitions = tfod.getUpdatedRecognitions();
        }
        if(newUpdatedRecognitions != null) {
            updatedRecognitions = newUpdatedRecognitions;
        }
        if(updatedRecognitions != null) {
            this.telemetry.addData("# Objects Detected", updatedRecognitions.size());
            for (Recognition recognition : updatedRecognitions) {
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );

            }
        }

    }
    @Override
    public void periodic() {
        DetectObjects();
    }
    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
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
}
