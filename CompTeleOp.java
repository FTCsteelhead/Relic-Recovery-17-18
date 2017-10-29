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
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.CRServo;
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
@TeleOp

public class CompTeleOp extends LinearOpMode {
    //private CRServo lwservo;
    //private CRServo rwservo;
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private DcMotor upMotor;
    private Servo armLservo;
    private Servo armRservo;
    private GyroSensor gyro;
    private Servo lservo;
    private Servo rservo;


    @Override
    public void runOpMode() {
        //lwservo = hardwareMap.get(CRServo.class, "lwservo");
        //rwservo = hardwareMap.get(CRServo.class, "rwservo");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        gyro = hardwareMap.get(GyroSensor.class, "gyro");
        upMotor = hardwareMap.get(DcMotor.class, "upMotor");
        armLservo = hardwareMap.get(Servo.class, "armLservo");
        armRservo = hardwareMap.get(Servo.class, "armRservo");
        lservo = hardwareMap.get(Servo.class, "lservo");
        rservo = hardwareMap.get(Servo.class, "rservo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // initialize motors and servos
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rservo.setPosition(1);
        lservo.setPosition(0);
        
        //initialize arms
        armLservo.setPosition(1);
        armRservo.setPosition(1);
        
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            /*set cont. servos
            if (gamepad1.left_bumper) {
            lwservo.setPower(-1);
            rwservo.setPower(1);
            }*/
            
            // set motor power
            leftMotor.setPower(gamepad1.left_stick_y);
            rightMotor.setPower(gamepad1.right_stick_y);
            

            
            //servo squeeze
            if (gamepad1.right_bumper) {
                rservo.setPosition(0);
                lservo.setPosition(1);
            }
            else {
                rservo.setPosition(1);
                lservo.setPosition(0);
            }
                
                
            
            telemetry.addData("right_bumper", gamepad1.right_bumper);
            telemetry.addData("rightMotor", rightMotor.getPower());
            telemetry.addData("leftMotor", leftMotor.getPower());
            telemetry.update();
            

        }
    }
}
