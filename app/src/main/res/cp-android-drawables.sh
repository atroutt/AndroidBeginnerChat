#!/bin/bash

# 1. PUT THIS SCRIPT IN YOUR APP's RES folder, for example /Users/myname/Developer/MyAndroidProject/app/src/main/res

# 2. UPDATE THIS VARIABLE to point to your local clone of https://github.com/google/material-design-icons
# if you don't alread have it git clone git@github.com:google/material-design-icons.git
basefolder="/Users/audrey/Developer/material-design-icons"

# 3. Find Material Icons on https://design.google.com/icons/ and note the group and icon name and use this script to suck them into your project
if [ $# -lt 2 ]; then
    echo "Usage: `basename $0` <icon group> <icon name> <(optional, default black) white/black> <(optional, default 24) 18/24/36/48>"
    exit 1
fi

if [ ! -d "$basefolder" ]; then
  echo "ERROR: $basefolder does not exist. Update the script to point to your local clone of https://github.com/google/material-design-icons"
  exit 1
fi

color="black"
if [ -n "$3" ]; then
   case $3 in
       white)
           color="white" ;;
       black)
           color="black" ;;
       *)
           echo "ERROR: $3 is not a valid color. Choose white or black."
           exit 1
   esac
   color=$3
fi

size="24dp"
if [ -n "$4" ]; then
    size="$4dp"
    case $4 in
        18)
            ;;
        24)
            ;;
        18)
            ;;
        24)
            ;;
        *)
            echo "ERROR: $4 is not a valid size. Choose 18, 24, 36, or 48."
            exit 1
    esac
fi

if [ ! -d "$basefolder/$1" ]; then
  echo "ERROR: $1 is not a icon group. Choose from:"
  for i in $(ls -d $basefolder/*/); do echo "   $(basename "$i")"; done
  exit 1
fi

for f in $basefolder/$1/drawable-mdpi; do
    d=$(basename "$f")
    echo "looking for $f/$2_${color}_$size.png"
    if [ -f "$f/$2_${color}_$size.png" ]; then
        echo "copying $f/$2_${color}_$size.png"
        mkdir -vp $d
        cp -v "$f/$2_${color}_$size.png" "$d/$2.png"
    fi
done

for f in $basefolder/$1/drawable-hdpi; do
    d=$(basename "$f")
    if [ -f "$f/$2_${color}_$size.png" ]; then
        echo "copying $f/$2_${color}_$size.png"
        mkdir -vp $d
        cp -v "$f/$2_${color}_$size.png" "$d/$2.png"
    fi
done

for f in $basefolder/$1/drawable-xxhdpi; do
    d=$(basename "$f")
    if [ -f "$f/$2_${color}_$size.png" ]; then
        echo "copying $f/$2_${color}_$size.png"
        mkdir -vp $d
        cp -v "$f/$2_${color}_$size.png" "$d/$2.png"
    fi
done

for f in $basefolder/$1/drawable-xxhdpi; do
    d=$(basename "$f")
    if [ -f "$f/$2_${color}_$size.png" ]; then
        echo "copying $f/$2_${color}_$size.png"
        mkdir -vp $d
        cp -v "$f/$2_${color}_$size.png" "$d/$2.png"
    fi
done

for f in $basefolder/$1/drawable-xxxhdpi; do
    d=$(basename "$f")
    if [ -f "$f/$2_${color}_$size.png" ]; then
        echo "copying $f/$2_${color}_$size.png"
        mkdir -vp $d
        cp -v "$f/$2_${color}_$size.png" "$d/$2.png"
    fi
done
