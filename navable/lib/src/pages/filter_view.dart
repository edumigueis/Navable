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
        padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 20),
        children: [
          Align(
            alignment: Alignment.centerLeft,
            child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
                child: Text('Filtrar',
                    style: Theme.of(context).textTheme.subtitle)),
          ),
          Padding(
              padding: const EdgeInsets.symmetric(horizontal: 10),
              child: ReviewSlider(
                initialValue: 2,
                onChanged: (newValue) {},
              )),
          ExpandableSection(
              title: 'Acessibilidade',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory(1, "a", "b"),
                AccessibilityCategory(1, "b", "b"),
                AccessibilityCategory(1, "c", "a")
              ], onSelectionChanged: (selectedCategories){},)),
          ExpandableSection(
              title: 'Tipos de estabeleciemento',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory(1, "a", "b"),
                AccessibilityCategory(1, "b", "b"),
                AccessibilityCategory(1, "c", "a")
              ], onSelectionChanged: (selectedCategories) => {},)),
          SizedBox(height: 30),
          NavableButton(
            "APLICAR",
            onPressed: () {
              String selectedData = "Some Filter Data";
              Navigator.pop(context, selectedData); // Pass data back
            },
          ),
        ],
      ),
    );
  }
}
