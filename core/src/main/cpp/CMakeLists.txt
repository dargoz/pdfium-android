# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Declares and names the project.

project("jniPdfium")

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

message(${log-lib})

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
message(${ANDROID_ABI})
message(${CMAKE_SOURCE_DIR})

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
add_library(aospPdfium SHARED IMPORTED)
set_target_properties(aospPdfium PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libmodpdfium.so)

add_library(libc++_shared SHARED IMPORTED)
set_target_properties(libc++_shared PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libc++_shared.so)

add_library(libmodft2 SHARED IMPORTED)
set_target_properties(libmodft2 PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libmodft2.so)

add_library(libmodpng SHARED IMPORTED)
set_target_properties(libmodpng PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libmodpng.so)

add_library(jniPdfium SHARED native-lib.cpp)
target_include_directories(jniPdfium PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/include)
target_compile_definitions(jniPdfium PUBLIC -DHAVE_PTHREADS)
target_link_libraries(jniPdfium PUBLIC log android jnigraphics aospPdfium libmodft2 libc++_shared libmodpng)

