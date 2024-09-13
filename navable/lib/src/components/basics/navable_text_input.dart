import 'package:flutter/material.dart';

import '../../util/styles.dart';

class NavableTextInput extends StatelessWidget {
  final String labelText;
  final String? hintText;
  final TextEditingController controller;

  const NavableTextInput(
      this.labelText, {
        super.key,
        required this.controller,
        this.hintText
      });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Container(
          margin: const EdgeInsets.only(bottom: 2.0),
          child: Text(
            labelText,
            style: const TextStyle(
              fontSize: 16.0,
              fontFamily: 'Ubuntu',
              fontWeight: FontWeight.w500,
              color: NavableColors.black,
            ),
            textAlign: TextAlign.start,
          ),
        ),
        TextField(
          controller: controller, // Utiliza o controlador
          decoration: InputDecoration(
            focusedBorder: const OutlineInputBorder(
              borderSide: BorderSide(
                color: NavableColors.grayAccent,
                width: 2.0,
              ),
              borderRadius: BorderRadius.all(Radius.circular(10)),
            ),
            enabledBorder: const OutlineInputBorder(
              borderSide: BorderSide(
                color: NavableColors.gray,
                width: 2.0,
              ),
              borderRadius: BorderRadius.all(Radius.circular(10)),
            ),
            hintText: hintText ?? "",
          ),
        ),
      ],
    );
  }
}
