# ihmc_sake_ros2
A ROS 2 package for controlling SAKE Robotics EZGrippers
Includes ROS 2 interfaces for commands and statuses, and helpful Java classes.

## Using ihmc_sake_ros2 as a Git Submodule
You may add ihmc_sake_ros2 into your ROS 2 workspace as a git submodule as such:

```shell
# cd into the ROS 2 workspace source directory
cd <your_ros2_ws>/src

# Clone ihmc_psyonic_ros2 and set it up as a git submodule
git submodule add https://github.com/ihmcrobotics/ihmc_sake_ros2.git

# Ensure the submodule is initialized as a git repository locally
git submodule init

# The following is not necessary, but may be useful to set
git config --global submodule.recurse true
```
