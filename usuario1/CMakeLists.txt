cmake_minimum_required(VERSION 3.14)
project(practica0301p01C)

set(CMAKE_CXX_STANDARD 11)


add_executable(P0301p01 Directory.h Directory.cpp FSObject.h FSObject.cpp TextFile.cpp TextFile.h FileManager.cpp FileManager.h Principal.cpp)

add_executable(P0301p01_tests catch.hpp Tests.cpp Directory.h Directory.cpp FSObject.h FSObject.cpp TextFile.cpp TextFile.h FileManager.cpp FileManager.h)


 List