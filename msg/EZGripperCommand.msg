byte LEFT=0
byte RIGHT=1

# Specifies the side of the robot of the gripper being referred to
byte robot_side 255

# Request the gripper to perform a calibration sequence
bool calibrate

# Request to reset the gripper error state after overheating
bool reset_errors

# Set to true to enable automatic cooldown
bool enable_auto_cooldown

# Set to true to override the cooldown
bool override_cooldown

# The goal position
# 0.0 = fully closed, 1.0 = fully open
float32 goal_position

# Maximum effort to put into achieving the goal position
# 0.0 = no effort (fingers will not move), 1.0 = maximum effort (can quickly overheat actuator)
# 0.3 is a reasonable normal value, and it is recommended not to exceed 0.8.
float32 max_effort

# Keeping the torque off when not needed can help keep the gripper's temperature down
bool torque_on
