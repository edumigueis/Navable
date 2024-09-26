import 'package:flutter/material.dart';

import '../../util/styles.dart';

class NavableButton extends StatelessWidget {
  final String text;
  final VoidCallback onPressed;
  final Color? background;
  final double? height;
  final double? width;
  final double? textSize;

  const NavableButton(
    this.text, {
    super.key,
    required this.onPressed,
    this.background,
        this.height,
        this.width,
        this.textSize
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: width ?? double.infinity,
      height: height ?? 45,
      child: TextButton(
        style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all<Color>(
              background == null ? NavableColors.yellowAccent : background!),
          shape: WidgetStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10.0),
            ),
          ),
          side: WidgetStateProperty.all<BorderSide>(
            const BorderSide(color: NavableColors.black, width: 2.0),
          ),
          overlayColor: WidgetStateProperty.all<Color>(
              NavableColors.black.withOpacity(0.1)),
          elevation: WidgetStateProperty.all<double>(5),
        ),
        onPressed: onPressed,
        child: AnimatedSwitcher(
          duration: const Duration(milliseconds: 200),
          transitionBuilder: (child, animation) {
            return ScaleTransition(scale: animation, child: child);
          },
          child: Text(text,
              key: ValueKey<String>(text),
              style: Theme.of(context).textTheme.button.copyWith(fontSize: textSize ?? 18)),
        ),
      ),
    );
  }
}
