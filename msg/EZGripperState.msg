byte LEFT=0
byte RIGHT=1

# Specifies the side of the robot of the gripper being referred to
byte robot_side 255

# Temperature of the Dynamixel in Celsius
uint8 temperature

# The current Dynamixel position
# 0.0 = fully closed, 1.0 = fully open
float32 current_position

# The current amount of effort being used
# 0.0 = no effort, 1.0 = maximum effort
float32 current_effort

# Dynamixel's error codes
# See: https://emanual.robotis.com/docs/en/dxl/protocol1/#error
uint8 error_codes

# Realtime tick of the Dynamixel
# If this value isn't changing, communication with the gripper is broken
int32 realtime_tick

bool is_calibrated

bool is_calibrating

bool is_cooling_down

bool automatic_cooldown_enabled
