package SteelheadCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.GyroSensor;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@Autonomous

public class Auto2 extends LinearOpMode {
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private GyroSensor gyro;
   // private Servo leftservo;
   // private Servo rightservo;


    @Override
    public void runOpMode() {
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        gyro = hardwareMap.get(GyroSensor.class, "gyro");
        //leftservo = hardwareMap.get(Servo.class, "leftservo");
        //rightservo = hardwareMap.get(Servo.class, "rightservo");
        
    //initialize encoders and GyroSensor
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        
        gyro.calibrate();
        sleep(5000);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        
                turnRight (90);
                
        while (opModeIsActive()) {
            
        }
    
            telemetry.addData("GyroSensor", gyro.getHeading());
            telemetry.update();

        
    }
    
    public int getHeading () {
        int error = 5;
        int heading = gyro.getHeading();
        if (heading > 360-error) {
                heading = 0;
        }
        return heading;
    }
    
    //turn F(x)
    public void turnRight(int degrees) {
        int heading=getHeading();
        int target=heading+degrees;
        target = target%360;
        while (heading<target) {
            
                rightMotor.setPower(0.45);
                leftMotor.setPower(-0.45);
                
                telemetry.addData("intitial", heading);
                telemetry.addData("gyro", gyro.getHeading());
                telemetry.addData("target", target);
                telemetry.update();
                heading=getHeading();
        }    
                
        rightMotor.setPower(0);
        leftMotor.setPower(0); 
       
    }
}
