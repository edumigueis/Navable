import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class NavableColors {
  static const Color yellowAccent = Color(0xFFEDF67D);
  static const Color blueAccent = Color(0xFF5E2BFF);
  static const Color grayAccent = Color(0xFF35605A);
  static const Color gray = Color.fromARGB(255, 217, 231, 230);
  static const Color darkGray = Color.fromARGB(255, 80, 85, 85);
  static const Color black = Color(0xFF04030F);
  static const Color white = Color(0xFFF3FAE1);
}

extension CustomStyles on TextTheme {
  // Error style with line-through and bold
  TextStyle get error => GoogleFonts.urbanist(
          textStyle: const TextStyle(
        decoration: TextDecoration.lineThrough,
        fontSize: 20.0,
        color: Colors.red,
        fontWeight: FontWeight.bold,
      ));

  // Heading style with larger font size, custom font, and bold
  TextStyle get heading => GoogleFonts.urbanist(
          textStyle: const TextStyle(
        fontSize: 35.0,
        fontWeight: FontWeight.bold,
        color: NavableColors.black,
      ));

  // Subtitle style with medium font size and semi-bold weight
  TextStyle get subtitle => GoogleFonts.urbanist(
          textStyle: const TextStyle(
        fontSize: 28.0,
        fontWeight: FontWeight.w600,
        color: NavableColors.black,
      ));

  TextStyle get smalltitle => GoogleFonts.urbanist(
      textStyle: const TextStyle(
        fontSize: 18.0,
        fontWeight: FontWeight.w600,
        color: NavableColors.black,
      ));

  TextStyle get minititle => GoogleFonts.urbanist(
          textStyle: const TextStyle(
        fontSize: 15.0,
        fontWeight: FontWeight.w500,
        color: NavableColors.darkGray,
      ));

  // Body text style with normal weight
  TextStyle get body => GoogleFonts.urbanist(
          textStyle: const TextStyle(
        fontSize: 18.0,
        fontWeight: FontWeight.normal,
        color: NavableColors.darkGray,
      ));

  // Caption style with smaller font size and lighter weight
  TextStyle get caption => GoogleFonts.urbanist(
          textStyle: const TextStyle(
        fontSize: 14.0,
        fontWeight: FontWeight.w300,
        color: NavableColors.gray,
      ));

  // Caption style with smaller font size and lighter weight
  TextStyle get darkCaption => GoogleFonts.urbanist(
      textStyle: const TextStyle(
        fontSize: 13.0,
        fontWeight: FontWeight.w500,
        color: NavableColors.black,
      ));

  // Button style with all caps, bold, and specific color
  TextStyle get button => GoogleFonts.cabinCondensed(
          textStyle: const TextStyle(
        fontSize: 18.0,
        fontWeight: FontWeight.bold,
        color: NavableColors.black,
        letterSpacing: 0.9,
      ));

  TextStyle get highlight => GoogleFonts.cabinCondensed(
      textStyle: const TextStyle(
        fontSize: 14.0,
        fontWeight: FontWeight.w400,
        color: NavableColors.black,
        letterSpacing: 1,
      ));
}
