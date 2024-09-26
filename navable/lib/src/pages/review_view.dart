import 'package:flutter/material.dart';
import 'package:navable/src/components/accessibility_checks.dart';
import 'package:navable/src/components/basics/navable_button.dart';
import 'package:navable/src/components/basics/navable_text_input.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/models/place.dart';

import 'models/acc_category.dart';

class ReviewView extends StatefulWidget {
  ReviewView({super.key, required this.controller});

  static const routeName = '/review';

  final ProfileController controller;

  @override
  _ReviewViewState createState() => _ReviewViewState();
}

class _ReviewViewState extends State<ReviewView> {
  double sliderValue = 0;

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as Place;
    return Scaffold(
      extendBodyBehindAppBar: true,
      extendBody: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      body: ListView(
        children: [
          // Centered text at the top
          Center(
            child: Text(
              'Avaliar ${args.name}',
              style: const TextStyle(
                fontSize: 25.0,
                fontFamily: 'Ubuntu',
                fontWeight: FontWeight.bold,
                color: Color.fromRGBO(40, 40, 40, 1),
              ),
            ),
          ),
          // Slider with 3 possible values
          Padding(
              padding:
                  const EdgeInsets.symmetric(horizontal: 15.0, vertical: 15),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  getIconForValue(sliderValue),
                  const SizedBox(width: 10),
                  Expanded(
                    child: Slider(
                      value: sliderValue,
                      min: 0,
                      max: 2,
                      divisions: 2,
                      onChanged: (newValue) {
                        setState(() {
                          sliderValue = newValue;
                        });
                      },
                      activeColor: getColorForValue(sliderValue),
                    ),
                  ),
                ],
              )),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15.0),
            child: NavableTextInput(
              "O que achou da acessibilidade em ${args.name}?",
              controller: TextEditingController(),
              maxLines: 6,
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(15.0, 10.0, 15.0, 20.0),
            child: AccessibilityChecks(title: "", buttons: [
              AccessibilityCategory("a", "b"),
              AccessibilityCategory("b", "b"),
              AccessibilityCategory("c", "a")
            ]),
          ),
          Padding(
              padding: const EdgeInsets.symmetric(horizontal: 15.0),
              child: NavableButton("AVALIAR", onPressed: () {
                Navigator.pop(context);
              }))
        ],
      ),
    );
  }

  // Function to get the color based on the slider value
  Color getColorForValue(double value) {
    if (value == 0) {
      return Colors.red;
    } else if (value == 1) {
      return Colors.yellow;
    } else {
      return Colors.green;
    }
  }

  // Function to get the icon based on the slider value
  Icon getIconForValue(double value) {
    if (value == 0) {
      return Icon(Icons.close, color: Colors.red);
    } else if (value == 1) {
      return Icon(Icons.thumb_up, color: Colors.yellow);
    } else {
      return Icon(Icons.favorite, color: Colors.green);
    }
  }
}
