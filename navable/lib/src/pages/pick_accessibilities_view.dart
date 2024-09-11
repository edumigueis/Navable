import 'package:flutter/material.dart';
import 'package:navable/src/components/accessibility_checks.dart';
import 'package:navable/src/components/navable_button.dart';
import 'package:navable/src/pages/models/acc_category.dart';
import 'controllers/settings_controller.dart';

class PickAccessibilitiesView extends StatelessWidget {
  const PickAccessibilitiesView({super.key, required this.controller});

  static const routeName = '/pickacc';

  final SettingsController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      extendBody: true,
      backgroundColor: Colors.white,
      body: Center(
          child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
            const Padding(
              padding: EdgeInsets.fromLTRB(15.0, 4.0, 0.0, 15.0),
              child: Text("Quais as suas necessidades?"),
            ),
            Expanded(
                child: AccessibilityChecks(title: "", buttons: [
              AccessibilityCategory("a", "b"),
              AccessibilityCategory("b", "b"),
              AccessibilityCategory("c", "a")
            ])),
            Padding(
                padding: const EdgeInsets.fromLTRB(20.0, 0, 20.0, 20.0),
                child: NavableButton("NEXT", onPressed: () {
                  Navigator.pushNamed(context, "/home");
                }))
          ])),
    );
  }
}