/*
Copyright 2017 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package SteelheadCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
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

public class Auto extends LinearOpMode {
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private GyroSensor gyro;
    private Servo leftservo;
    private Servo rightservo;


    @Override
    public void runOpMode() {
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        gyro = hardwareMap.get(GyroSensor.class, "gyro");
        leftservo = hardwareMap.get(Servo.class, "leftservo");
        rightservo = hardwareMap.get(Servo.class, "rightservo");
        
    //initialize encoders and GyroSensor
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gyro.calibrate();
        sleep(500);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            if (rightMotor.getCurrentPosition()<=2400) {
                rightMotor.setPower(0.45);
                leftMotor.setPower(-0.45);
            }
            else {
                turnLeft (90); 
            }
            telemetry.addData("Encoders", rightMotor.getCurrentPosition());
            telemetry.addData("GyroSensor", gyro.getHeading());
            telemetry.update();

        }
    }
    
    
    //turn F(x)
    public void turnLeft(int degrees) 
    {
        if (gyro.getHeading() < degrees) {
                rightMotor.setPower(0.45);
                leftMotor.setPower(0.45);
        }
        else {
              rightMotor.setPower(0);
                leftMotor.setPower(0);  
        }   
    }
    
    
    //turn F(x)
    public void turnRight(int degrees) 
    {
        if (gyro.getHeading() > (360-degrees)) {
                rightMotor.setPower(-0.45);
                leftMotor.setPower(-0.45);
        }
        else {
              rightMotor.setPower(0);
                leftMotor.setPower(0);  
        }   
    }
    
}