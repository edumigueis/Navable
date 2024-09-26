import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_button.dart';
import 'package:navable/src/util/styles.dart';

import '../components/accessibility_checks.dart';
import '../components/basics/expandable_section.dart';
import '../components/review_slider.dart';
import 'models/acc_category.dart';

class FilterView extends StatelessWidget {
  const FilterView({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      child: ListView(
        shrinkWrap: true,
        padding: EdgeInsets.symmetric(horizontal: 15, vertical: 20),
        children: [
          Align(
            alignment: Alignment.centerLeft,
            child: Text('Filtrar', style: Theme.of(context).textTheme.subtitle),
          ),
          ReviewSlider(
            initialValue: 2,
            onChanged: (newValue) {},
          ),
          ExpandableSection(
              title: 'Acessibilidade',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory("a", "b"),
                AccessibilityCategory("b", "b"),
                AccessibilityCategory("c", "a")
              ])),
          ExpandableSection(
              title: 'Tipos de estabeleciemento',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory("a", "b"),
                AccessibilityCategory("b", "b"),
                AccessibilityCategory("c", "a")
              ])),
          NavableButton(
              "APLICAR",
              onPressed: () {
                // Example data to return
                String selectedData = "Some Filter Data";
                Navigator.pop(context, selectedData); // Pass data back
              },
            ),
        ],
      ),
    );
  }
}
