import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_button.dart';
import 'package:navable/src/components/basics/navable_text_input.dart';
import 'package:navable/src/components/review_slider.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/util/styles.dart';

class ReviewView extends StatefulWidget {
  const ReviewView({super.key, required this.controller});

  static const routeName = '/review';

  final ProfileController controller;

  @override
  ReviewViewState createState() => ReviewViewState();
}

class ReviewViewState extends State<ReviewView> {
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
        shrinkWrap: true,
        children: [
          // Centered text at the top
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15.0, vertical: 15),
            child: Text('Avaliar', style: Theme.of(context).textTheme.subtitle),
          ),
          // Slider with 3 possible values
          Padding(
              padding:
                  const EdgeInsets.symmetric(horizontal: 15.0, vertical: 15),
              child: ReviewSlider(
                initialValue: sliderValue,
                onChanged: (newValue) {
                  setState(() {
                    sliderValue = newValue;
                  });
                },
              )),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15.0, vertical: 10),
            child: NavableTextInput(
              "O que achou da acessibilidade em ${args.name}?",
              controller: TextEditingController(),
              maxLines: 6,
            ),
          ),
          /*Padding(
            padding: const EdgeInsets.fromLTRB(15.0, 10.0, 15.0, 20.0),
            child: Expanded(child: AccessibilityChecks(title: "", buttons: [
              AccessibilityCategory(1, "a", "b"),
              AccessibilityCategory(1, "b", "b"),
              AccessibilityCategory(1, "c", "a")
            ], onSelectionChanged: (selectedCategories){},),
          )),*/
          Padding(
              padding:
                  const EdgeInsets.symmetric(horizontal: 15.0, vertical: 15),
              child: NavableButton("AVALIAR", onPressed: () {
                Navigator.pop(context);
              }))
        ],
      ),
    );
  }
}
