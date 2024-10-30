import 'package:flutter/material.dart';

import '../../util/styles.dart';

class NavableTextInput extends StatelessWidget {
  final String labelText;
  final String? hintText;
  final int? maxLines;
  final TextEditingController controller;
  final bool? obscureText;

  const NavableTextInput(this.labelText,
      {super.key, required this.controller, this.hintText, this.maxLines, this.obscureText});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Container(
          margin: const EdgeInsets.only(bottom: 2.0),
          child: Text(
            labelText,
            style: Theme.of(context).textTheme.minititle,
            textAlign: TextAlign.start,
          ),
        ),
        TextField(
            obscureText: obscureText ?? false,
            controller: controller,
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
                hintStyle: Theme.of(context).textTheme.caption),
            maxLines: maxLines ?? 1),
      ],
    );
  }
}
