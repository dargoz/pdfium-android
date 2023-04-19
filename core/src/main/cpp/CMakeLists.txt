# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Declares and names the project.

project("pdf-android")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

message(${CMAKE_SOURCE_DIR})
add_library(aospPdfium SHARED IMPORTED)
set_target_properties(aospPdfium PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libmodpdfium.so)

add_library(libmodc++_shared SHARED IMPORTED)
set_target_properties(libmodc++_shared PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libc++_shared.so)

add_library(libmodft2 SHARED IMPORTED)
set_target_properties(libmodft2 PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libmodft2.so)

add_library(libmodpng SHARED IMPORTED)
set_target_properties(libmodpng PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/lib/${ANDROID_ABI}/libmodpng.so)

add_library(jniPdfium SHARED native-lib.cpp)
target_include_directories(jniPdfium PRIVATE include)
target_compile_definitions(jniPdfium PRIVATE -DHAVE_PTHREADS)
target_link_libraries(jniPdfium PRIVATE log android jnigraphics aospPdfium)


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

#if (${ANDROID_ABI} STREQUAL "armeabi-v7a")
#    target_link_libraries( # Specifies the target library.
#            ${PROJECT_NAME}
#
#            # Links the target library to the log library
#            # included in the NDK.
#            ${log-lib}
#            android
#            libpdfium.cr_armeabi-v7a)
#elseif (${ANDROID_ABI} STREQUAL "arm64-v8a")
#    target_link_libraries( # Specifies the target library.
#            ${PROJECT_NAME}
#
#            # Links the target library to the log library
#            # included in the NDK.
#            ${log-lib}
#            android
#            libpdfium.cr_arm64-v8a)
#elseif (${ANDROID_ABI} STREQUAL "x86")
#    target_link_libraries( # Specifies the target library.
#            ${PROJECT_NAME}
#
#            # Links the target library to the log library
#            # included in the NDK.
#            ${log-lib}
#            android
#            libpdfium.cr_x86)
#elseif (${ANDROID_ABI} STREQUAL "x86_64")
#    target_link_libraries( # Specifies the target library.
#            ${PROJECT_NAME}
#
#            # Links the target library to the log library
#            # included in the NDK.
#            ${log-lib}
#            android
#            libpdfium.cr_x86_64)
#endif ()
