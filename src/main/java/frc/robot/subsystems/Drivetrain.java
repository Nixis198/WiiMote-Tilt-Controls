package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;

public class Drivetrain {
    //Masters
    public static WPI_VictorSPX rightVictorMaster = new WPI_VictorSPX(RobotMap.rightVictorMasterCAN);
    public static WPI_VictorSPX leftVictorMaster = new WPI_VictorSPX(RobotMap.leftVictorMasterCAN);
    //Slaves
    public static WPI_VictorSPX rightVictorSlave = new WPI_VictorSPX(RobotMap.rightVicrotSlaveCAN);
    public static WPI_VictorSPX leftVictorSlave = new WPI_VictorSPX(RobotMap.leftVictorSlaveCAN);

    //Differential Drive
    public static DifferentialDrive drive = new DifferentialDrive(rightVictorMaster, leftVictorMaster);

    public static void victorSetup() {
        //Reset the victors so they dont die
        rightVictorMaster.configFactoryDefault();
        rightVictorSlave.configFactoryDefault();
        leftVictorMaster.configFactoryDefault();
        leftVictorSlave.configFactoryDefault();

        //Follow so the slaves follow the master
        rightVictorSlave.follow(rightVictorMaster);
        leftVictorSlave.follow(leftVictorMaster);

        //Invert so incase the motors are backwards
        //if robot does not drive stright try changing the invert first then the right side invert at the bottom
        rightVictorMaster.setInverted(false);
        rightVictorSlave.setInverted(InvertType.FollowMaster);
        leftVictorMaster.setInverted(false);
        leftVictorSlave.setInverted(InvertType.FollowMaster);

        //fixes the thing where the right side is said to be negative
        frc.robot.subsystems.Drivetrain.drive.setRightSideInverted(true);
    }
}