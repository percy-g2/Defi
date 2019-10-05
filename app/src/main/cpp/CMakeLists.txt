cmake_minimum_required(VERSION 3.4.1)

add_library(defi-jni SHARED
            defi-jni.c)

# Include libraries needed for mimba-jni lib
target_link_libraries(defi-jni
                      android
log)
